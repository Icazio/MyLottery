package cn.icarus.lottery.domain.strategy.repository;

import cn.icarus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.icarus.lottery.infrastructure.po.Award;

public interface IStrategyRepository {
    StrategyRich queryStrategyRich(Long strategyId);

    Award queryAwardInfo(String awardId);
}
