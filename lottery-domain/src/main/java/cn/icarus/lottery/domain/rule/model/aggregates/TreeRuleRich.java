package cn.icarus.lottery.domain.rule.model.aggregates;

import cn.icarus.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.icarus.lottery.domain.rule.model.vo.TreeRootVO;

import java.util.Map;

/**
 * @author Icarus
 * @description 规则树信息聚合【树根信息、树节点地图[节点ID->子节点]】
 * @date 2023/4/8 10:01
 */
public class TreeRuleRich {

    /**树根信息*/
    private TreeRootVO treeRoot;

    /**树节点ID->子节点*/
    private Map<Long, TreeNodeVO> treeNodeMap;


    public TreeRootVO getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRootVO treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNodeVO> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNodeVO> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }
}
