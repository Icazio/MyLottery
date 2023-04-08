package cn.icarus.lottery.domain.rule.model.vo;

/**
 * @author Icarus
 * @description 规则树节点值对象
 * @date 2023/4/7 21:35
 */
public class TreeNodeLineVO {

    /**节点来源*/
    private Long nodeIdFrom;

    /**节点去向*/
    private Long nodeIdTo;

    /**限定类型：①=;②>;③<;④>=;⑤<=;⑥enum[枚举范围]*/
    private Integer ruleLimitType;

    /**限定值*/
    private String ruleLimitValue;

    @Override
    public String toString() {
        return "TreeNodeLineVO{" +
                "nodeIdFrom=" + nodeIdFrom +
                ", nodeIdTo=" + nodeIdTo +
                ", ruleLimitType=" + ruleLimitType +
                ", ruleLimitValue='" + ruleLimitValue + '\'' +
                '}';
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
