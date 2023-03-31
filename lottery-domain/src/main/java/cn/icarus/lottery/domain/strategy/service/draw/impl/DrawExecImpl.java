package cn.icarus.lottery.domain.strategy.service.draw.impl;

import cn.icarus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import cn.icarus.lottery.domain.strategy.service.draw.AbstractDrawBase;
import cn.icarus.lottery.domain.strategy.service.draw.IDrawExec;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Icarus
 * @description 抽奖过程方法实现
 * @date 2023/3/25 23:06
 */
@Service("iDrawExec")
public class DrawExecImpl extends AbstractDrawBase implements IDrawExec {

    private Logger logger= LoggerFactory.getLogger(DrawExecImpl.class);

    /**
     * 查询无库存的奖品ID
     * @param strategyId 策略ID
     * @return  无库存排除奖品列表ID集合
     */
    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
        List<String > awardList= strategyRepository.queryNoStockStrategyAwardList(strategyId);
        logger.info("执行抽奖策略 strategyId:{},无库存排除奖品列表ID集合 awardList:{}",strategyId, JSON.toJSONString(awardList));
        return awardList;
    }

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm drawAlgorithm, List<String> excludeAwardIds) {
        //执行抽奖
        String awardId=drawAlgorithm.randomDraw(strategyId,excludeAwardIds);

        //判断抽奖结果
        if(awardId==null) {
            return null;
        }

        /*
         * 扣减库存，暂时采用数据库行级锁的方式进行扣减库存，后续优化为Redis分布式锁扣减decr/incr
         * 注意：通常数据库直接锁行记录的方式并不能支撑较大体量的并发，但此种方式需要了解，因为在分库分表下的正常数据流量下的个人数据记录中，是可以使用行级锁的，因为他只影响到自己的记录，不会影响到其他人
         */
        boolean isSuccess=strategyRepository.deductStock(strategyId,awardId);

        return isSuccess?awardId:null;
    }
}
