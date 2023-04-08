package cn.icarus.lottery.domain.rule.service.logic;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @author Icarus
 * @description  规则基础抽象类
 * @date 2023/4/7 21:21
 */
public abstract class BasicLogic implements ILogic{
    /**
     * 获得规则比对值
     * @param decisionMatterReq 决策物料
     * @return 比对值
     */
    @Override
    public abstract String matterValue(DecisionMatterReq decisionMatterReq);


    /**实现逻辑:遍历treeNodeLineList,如果其中的节点符合matterValue【规则比对值】对应的判断条件的话，就返回下一个节点*/
    @Override
    public Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList) {
        for(TreeNodeLineVO nodeLineVO:treeNodeLineInfoList){
            if(decisionLogic(matterValue,nodeLineVO)){
                return nodeLineVO.getNodeIdTo();
            }
        }
        //如果都不符合,说明树没节点
        return Constants.Global.TREE_NULL_NODE;
    }

    private boolean decisionLogic(String matterValue, TreeNodeLineVO nodeLineVO){
        switch (nodeLineVO.getRuleLimitType()){
            case Constants.RuleLimitType.EQUAL:
                return matterValue.equals(nodeLineVO.getRuleLimitValue());
            case Constants.RuleLimitType.GT:
                return Double.parseDouble(matterValue) > Double.parseDouble(nodeLineVO.getRuleLimitValue());
            case Constants.RuleLimitType.LT:
                return Double.parseDouble(matterValue) < Double.parseDouble(nodeLineVO.getRuleLimitValue());
            case Constants.RuleLimitType.GE:
                return Double.parseDouble(matterValue) >= Double.parseDouble(nodeLineVO.getRuleLimitValue());
            case Constants.RuleLimitType.LE:
                return Double.parseDouble(matterValue) <= Double.parseDouble(nodeLineVO.getRuleLimitValue());
            default:
                return false;
        }
    }
}
