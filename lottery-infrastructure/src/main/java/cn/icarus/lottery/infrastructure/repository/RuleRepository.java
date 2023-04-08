package cn.icarus.lottery.infrastructure.repository;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.icarus.lottery.domain.rule.model.vo.TreeNodeLineVO;
import cn.icarus.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.icarus.lottery.domain.rule.model.vo.TreeRootVO;
import cn.icarus.lottery.domain.rule.repository.IRuleRepository;
import cn.icarus.lottery.infrastructure.dao.IRuleTreeDao;
import cn.icarus.lottery.infrastructure.dao.IRuleTreeNodeDao;
import cn.icarus.lottery.infrastructure.dao.IRuleTreeNodeLineDao;
import cn.icarus.lottery.infrastructure.po.RuleTree;
import cn.icarus.lottery.infrastructure.po.RuleTreeNode;
import cn.icarus.lottery.infrastructure.po.RuleTreeNodeLine;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Icarus
 * @description 规则信息仓储服务【查询规则树聚合信息】
 * @date 2023/4/7 21:17
 */

@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private IRuleTreeDao ruleTreeDao;
    @Resource
    private IRuleTreeNodeDao ruleTreeNodeDao;
    @Resource
    private IRuleTreeNodeLineDao ruleTreeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        TreeRuleRich treeRuleRich=new TreeRuleRich();

        //规则树
        RuleTree ruleTree=ruleTreeDao.queryRuleTree(treeId);
        TreeRootVO treeRootVO=new TreeRootVO();
        treeRootVO.setTreeId(treeId);
        treeRootVO.setTreeName(ruleTree.getTreeName());
        treeRootVO.setTreeRootNodeId(ruleTree.getTreeRootNodeId());
        treeRuleRich.setTreeRoot(treeRootVO);


        //树节点->树连接线
        Map<Long, TreeNodeVO> treeNodeMap=new HashMap<>();
        List<RuleTreeNode> ruleTreeNodeList=ruleTreeNodeDao.queryRuleTreeNodeList(treeId);
        for(RuleTreeNode ruleTreeNode:ruleTreeNodeList) {
            TreeNodeVO treeNodeVO=new TreeNodeVO();
            treeNodeVO.setTreeId(treeId);
            treeNodeVO.setTreeNodeId(ruleTreeNode.getId());
            treeNodeVO.setRuleKey(ruleTreeNode.getRuleKey());
            treeNodeVO.setRuleDesc(ruleTreeNode.getRuleDesc());
            treeNodeVO.setNodeType(ruleTreeNode.getNodeType());
            treeNodeVO.setNodeValue(ruleTreeNode.getNodeValue());

            List<TreeNodeLineVO> treeNodeLineList = new ArrayList<>();
            //
            if(Constants.NodeType.STEM.equals(ruleTreeNode.getNodeType())) {
                RuleTreeNodeLine ruleTreeNodeLineReq=new RuleTreeNodeLine();
                ruleTreeNodeLineReq.setTreeId(treeId);
                ruleTreeNodeLineReq.setNodeIdFrom(ruleTreeNode.getId());
                List<RuleTreeNodeLine> ruleTreeNodeLineList = ruleTreeNodeLineDao.queryRuleTreeNodeLineList(ruleTreeNodeLineReq);

                for (RuleTreeNodeLine ruleTreeNodeLine : ruleTreeNodeLineList) {
                    TreeNodeLineVO treeNodeLine = new TreeNodeLineVO();
                    treeNodeLine.setNodeIdFrom(ruleTreeNodeLine.getNodeIdFrom());
                    treeNodeLine.setNodeIdTo(ruleTreeNodeLine.getNodeIdTo());
                    treeNodeLine.setRuleLimitType(ruleTreeNodeLine.getRuleLimitType());
                    treeNodeLine.setRuleLimitValue(ruleTreeNodeLine.getRuleLimitValue());
                    treeNodeLineList.add(treeNodeLine);
                }
            }
            treeNodeVO.setTreeNodeLineInfoList(treeNodeLineList);
            treeNodeMap.put(ruleTreeNode.getId(),treeNodeVO);
        }


        treeRuleRich.setTreeNodeMap(treeNodeMap);

        return treeRuleRich;
    }
}
