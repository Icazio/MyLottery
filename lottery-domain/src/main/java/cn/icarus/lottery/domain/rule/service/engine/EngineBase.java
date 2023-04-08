package cn.icarus.lottery.domain.rule.service.engine;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.rule.model.res.EngineResult;
import cn.icarus.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.icarus.lottery.domain.rule.model.vo.TreeRootVO;
import cn.icarus.lottery.domain.rule.service.logic.ILogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Icarus
 * @description 规则引擎基础类
 * @date 2023/4/8 9:45
 */
public abstract class EngineBase extends EngineConfig implements EngineFilter{
    private Logger logger= LoggerFactory.getLogger(EngineBase.class);


    /**TODO:确认为什么要这样写：：：：若未能实现重写*/
    @Override
    public EngineResult process(DecisionMatterReq matter) {
        throw new RuntimeException("未实现规则引擎服务");
    }

protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich,DecisionMatterReq matter){
    TreeRootVO treeRootVO= treeRuleRich.getTreeRoot();
    Map<Long,TreeNodeVO> treeNodeVOMap=treeRuleRich.getTreeNodeMap();


    Long rootNodeId=treeRootVO.getTreeRootNodeId();
    TreeNodeVO treeNodeInfo=treeNodeVOMap.get(rootNodeId);

    //节点类型【nodeType】:1子叶、2果实
    //直到找到果实节点以前，一直遍历
    while(Constants.NodeType.STEM.equals(treeNodeInfo.getNodeType())){
        String ruleKey= treeNodeInfo.getRuleKey();
        //获取逻辑引擎
        ILogic logic= logicMap.get(ruleKey);
        String matterValue= logic.matterValue(matter);
        //调用逻辑引擎内的比对方法
        Long nextNode=logic.filter(matterValue,treeNodeInfo.getTreeNodeLineInfoList());
        treeNodeInfo=treeNodeVOMap.get(nextNode);
        logger.info("决策树引擎=>{} userId:{} treeId:{} treeNode:{} ruleKey{} matterValue:{}",treeRootVO.getTreeName(),matter.getUserId(),matter.getTreeId(),treeNodeInfo.getTreeNodeId(),ruleKey,matterValue);
    }

    //找到果实节点
    return treeNodeInfo;
    }

}
