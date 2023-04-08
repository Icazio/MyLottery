package cn.icarus.lottery.infrastructure.po;

/**
 * @author Icarus
 * @description
 * @date 2023/4/8 11:29
 */
public class RuleTreeNodeLine {
    /**树ID*/
    private Long treeId;
    /**节点来源*/
    private Long nodeIdFrom;
    /**节点去向 子节点ID*/
    private Long nodeIdTo;
    /**规则类型*/
    private Integer ruleLimitType;
    /**规则限制值*/
    private String ruleLimitValue;


    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }

    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    public Integer getRuleLimitType() {
        return ruleLimitType;
    }

    public void setRuleLimitType(Integer ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    public String getRuleLimitValue() {
        return ruleLimitValue;
    }

    public void setRuleLimitValue(String ruleLimitValue) {
        this.ruleLimitValue = ruleLimitValue;
    }
}
