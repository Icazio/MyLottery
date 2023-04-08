package cn.icarus.lottery.domain.rule.model.vo;

/**
 * @author Icarus
 * @description 规则树的树根的配置【规则树ID、规则树名称、规则树根ID】
 * @date 2023/4/8 10:31
 */
public class TreeRootVO {

    /**规则树ID*/
    private Long treeId;
    /**规则树名称*/
    private String treeName;
    /**规则树根ID*/
    private Long treeRootNodeId;



    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "TreeRootVO{" +
                "treeId=" + treeId +
                ", treeName='" + treeName + '\'' +
                ", treeRootNodeId=" + treeRootNodeId +
                '}';
    }
}
