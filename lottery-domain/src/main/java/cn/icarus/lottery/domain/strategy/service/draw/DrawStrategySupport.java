package cn.icarus.lottery.domain.strategy.service.draw;

import cn.icarus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.icarus.lottery.domain.strategy.model.vo.AwardBriefVO;
import cn.icarus.lottery.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * @author Icarus
 * @description 抽奖策略数据支撑，一些通用的数据服务
 * @date 2023/3/26 17:30
 */
public class DrawStrategySupport extends  DrawConfig{
    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     *
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId){
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详情信息
     *
     * @param awardId 奖品ID
     * @return 中奖情况
     */
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }

}
