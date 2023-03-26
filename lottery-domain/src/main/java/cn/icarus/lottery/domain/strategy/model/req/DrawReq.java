package cn.icarus.lottery.domain.strategy.model.req;

/**
 * @description
 * @date 2023/3/25 12:24
 */
public class DrawReq {
    private String uId;//用户ID
    private Long strategyId;//策略ID

    public DrawReq() {
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
