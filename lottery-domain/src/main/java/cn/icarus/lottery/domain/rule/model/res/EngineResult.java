package cn.icarus.lottery.domain.rule.model.res;

/**
 * @author Icarus
 * @description 决策结果【执行结果、用户ID、规则树ID、果实节点ID、果实节点值】
 * @date 2023/4/8 9:33
 */
public class EngineResult {
    /**执行结果*/
    private boolean isSuccess;
    /**用户ID*/
    private String userId;
    /**规则树ID*/
    private Long treeId;
    /**果实节点ID*/
    private Long nodeId;
    /**果实节点值*/
    private String nodeValue;

    public EngineResult(String userId, Long treeId, Long nodeId, String nodeValue) {
        this.isSuccess = true;
        this.userId = userId;
        this.treeId = treeId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }
}
