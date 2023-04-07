package cn.icarus.lottery.domain.activity.model.vo;

/**
 * @author Icarus
 * @description 【活动单号、活动ID、活动状态、策略ID】
 * @date 2023/4/3 15:54
 */
public class UserTakeActivityVO {
    /**
     * 活动单号
     */
    private Long takeId;
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动参与状态：0未参加1已参加
     */
    private Integer state;

    /**
     * 策略ID
     */
    private Long strategyId;

    @Override
    public String toString() {
        return "UserTakeActivityVO{" +
                "takeId=" + takeId +
                ", activityId=" + activityId +
                ", state=" + state +
                ", strategyId=" + strategyId +
                '}';
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
