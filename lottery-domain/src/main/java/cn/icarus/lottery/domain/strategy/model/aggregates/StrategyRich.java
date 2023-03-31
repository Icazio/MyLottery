package cn.icarus.lottery.domain.strategy.model.aggregates;

import cn.icarus.lottery.domain.strategy.model.vo.StrategyBriefVO;
import cn.icarus.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;

import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/23 15:50
 */
public class StrategyRich {
    /**策略Id*/
    private Long strategyId;
    /**策略配置*/
    private StrategyBriefVO strategy;
    /**策略明细*/
    private List<StrategyDetailBriefVO> strategyDetailList;

    public StrategyRich() {
    }

    public StrategyRich(Long strategyId, StrategyBriefVO strategy, List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyId = strategyId;
        this.strategy = strategy;
        this.strategyDetailList = strategyDetailList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public StrategyBriefVO getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyBriefVO strategy) {
        this.strategy = strategy;
    }

    public List<StrategyDetailBriefVO> getStrategyDetailList() {
        return strategyDetailList;
    }

    public void setStrategyDetailList(List<StrategyDetailBriefVO> strategyDetailList) {
        this.strategyDetailList = strategyDetailList;
    }
}
