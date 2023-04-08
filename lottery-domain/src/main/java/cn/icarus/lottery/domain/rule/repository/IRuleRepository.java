package cn.icarus.lottery.domain.rule.repository;


import cn.icarus.lottery.domain.rule.model.aggregates.TreeRuleRich;

/**
 * @author Icarus
 * @description
 * @date 2023/4/8
 */
public interface IRuleRepository {

    /**
     * 通过RuleEngineHandler中的process方法内拿到的treeId,获取这棵规则树的聚合信息
     * @param treeId 规则树ID
     * @return       规则树聚合信息
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);
}
