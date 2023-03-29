package cn.icarus.lottery.domain.strategy.service.draw;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.icarus.lottery.domain.strategy.model.req.DrawReq;
import cn.icarus.lottery.domain.strategy.model.res.DrawResult;
import cn.icarus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.icarus.lottery.domain.strategy.model.vo.DrawAwardInfo;
import cn.icarus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.icarus.lottery.infrastructure.po.Award;
import cn.icarus.lottery.infrastructure.po.Strategy;
import cn.icarus.lottery.infrastructure.po.StrategyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Icarus
 * @description 定义抽象抽奖过程。模板模式
 * @date 2023/3/25 12:31
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{

    private Logger logger= LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {
        //1.获取抽奖策略
        StrategyRich strategyRich=super.queryStrategyRich(req.getStrategyId());
        Strategy strategy=strategyRich.getStrategy();

        //2.校验抽奖策略是否已经初始化到内存
        this.checkAndInitRateData(strategy.getStrategyId(),strategy.getStrategyMode(),strategyRich.getStrategyDetailList());

        //3.获取不在抽奖范围里的列表
        List<String> excludeAwardIds = this.queryExcludeAwardIds(strategyRich.getStrategyId());

        //4.执行抽奖算法
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmGroup.get(strategy.getStrategyMode()), excludeAwardIds);

        //5.包装并返回中奖结果
        return buildDrawResult(req.getuId(),req.getStrategyId(),awardId);
    }


    /**
     * 获取不在抽奖范围内的列表，
     * 包括：奖品库存为空、风控策略、临时调整
     * 这类数据是含有业务逻辑的，所以需要由具体的实现方式决定
     * @param strategyId 策略ID
     * @return 排除的奖品ID集合
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);


    /**
     * 执行抽奖算法
     * @param strategyId  策略ID
     * @param drawAlgorithm  抽奖算法模型
     * @param excludeAwardIds 排除的抽奖ID集合
     * @return 中奖奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm,List<String> excludeAwardIds);

    /**
     * 校验抽奖策略是否已经初始化到内存
     *
     * @param strategyId            抽奖策略ID
     * @param strategyMode          抽奖策略模式
     * @param strategyDetailList    抽奖策略详情
     */
    private void  checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList){


        // 策略方式「1:单项概率、2:总体概率」
        if(!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }
        IDrawAlgorithm drawAlgorithm=drawAlgorithmGroup.get(strategyMode);

        //已经初始化过的数据不必重复初始化
        if(drawAlgorithm.isExistRateTuple(strategyId)) {
            return;
        }

        //解析并初始化中奖概率到散列表
        List<AwardRateInfo> awardRateInfoList=new ArrayList<>(strategyDetailList.size());
        for(StrategyDetail strategyDetail:strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
        }

        drawAlgorithm.initRateTuple(strategyId,awardRateInfoList);

    }

    /**
     *  包装抽奖结果
     *
     * @param uId           用户ID
     * @param strategyId    策略ID
     * @param awardId       奖品ID 如果传入null,是并发抽奖情况下，库存临界值1->0 会有用户中奖结果为null
     * @return  中奖结果
     */
    private DrawResult buildDrawResult(String uId,Long strategyId, String awardId){
        if(awardId==null){
            logger.info("执行策略抽奖完成【未抽奖】，用户：{} 策略ID：{}",uId,strategyId);
            return new DrawResult(uId,strategyId,Constants.DrawState.FAIL.getCode());
        }

        Award award=super.queryAwardInfoByAwardId(awardId);
        DrawAwardInfo drawAwardInfo=new DrawAwardInfo(award.getAwardId(),award.getAwardName());
        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, awardId, award.getAwardName());

        return new DrawResult(uId,strategyId,Constants.DrawState.SUCCESS.getCode(),drawAwardInfo);
    }

}
