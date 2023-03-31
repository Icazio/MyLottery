package cn.icarus.lottery.domain.strategy.repository;

import cn.icarus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.icarus.lottery.domain.strategy.model.vo.AwardBriefVO;

import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/26
 */
public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    AwardBriefVO queryAwardInfo(String awardId);

    /**
     *
     *
     * @param strategyId
     * @return
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyId 策略ID
     * @param awardId   奖品ID
     * @return          扣减结果。成功返回true
     */
    boolean deductStock(Long strategyId,String awardId);
}
