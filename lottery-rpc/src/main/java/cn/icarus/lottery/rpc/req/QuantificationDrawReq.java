package cn.icarus.lottery.rpc.req;

import java.util.Map;

/**
 * @author Icarus
 * @description 量化人群抽奖请求【用户ID、规则树ID、规则】
 * @date 2023/4/9 10:27
 */
public class QuantificationDrawReq {

    private String uId;

    private Long treeId;

    private Map<String,Object> valMap;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Map<String, Object> getValMap() {
        return valMap;
    }

    public void setValMap(Map<String, Object> valMap) {
        this.valMap = valMap;
    }
}
