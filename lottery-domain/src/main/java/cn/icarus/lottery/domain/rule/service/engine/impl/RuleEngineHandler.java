package cn.icarus.lottery.domain.rule.service.engine.impl;

import cn.icarus.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.rule.model.res.EngineResult;
import cn.icarus.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.icarus.lottery.domain.rule.repository.IRuleRepository;
import cn.icarus.lottery.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Icarus
 * @description
 * @date 2023/4/8 9:51
 */
@Service("ruleEngineHandler")
public class RuleEngineHandler extends EngineBase {
    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        //获取决策规则树的所有信息
        TreeRuleRich treeRuleRich=ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if(treeRuleRich==null){
            throw new RuntimeException("Tree Rule is null");
        }

        //决策
        TreeNodeVO treeNodeVO=engineDecisionMaker(treeRuleRich,matter);

        //决策结果
        return new EngineResult(matter.getUserId(),treeNodeVO.getTreeId(),treeNodeVO.getTreeNodeId(),treeNodeVO.getNodeValue());



    }
}
