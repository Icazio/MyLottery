package cn.icarus.lottery.infrastructure.po;

/**
 * @author Icarus
 * @description
 * @date 2023/4/8 11:32
 */
public class RuleTree {
    /***/
    private String treeId;
    /***/
    private String treeName;
    /***/
    private String treeDesc;
    /***/
    private Long treeRootNodeId;

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeDesc() {
        return treeDesc;
    }

    public void setTreeDesc(String treeDesc) {
        this.treeDesc = treeDesc;
    }

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }
}
