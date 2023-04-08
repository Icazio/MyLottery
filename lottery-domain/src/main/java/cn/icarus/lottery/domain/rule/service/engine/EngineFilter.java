package cn.icarus.lottery.domain.rule.service.engine;


import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.rule.model.res.EngineResult;

/**
 * @author Icarus
 * @description 规则过滤器引擎
 * @date 2023/4/8
 */
public interface EngineFilter {
    /**
     * 规则过滤器接口
     * @param matter 规则决策物料
     * @return        规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);

}
