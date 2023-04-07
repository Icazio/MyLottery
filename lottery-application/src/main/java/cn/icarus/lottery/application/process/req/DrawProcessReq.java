package cn.icarus.lottery.application.process.req;

/**
 * @author Icarus
 * @description 抽奖请求【用户ID、活动ID】
 * @date 2023/4/3 19:12
 */
public class DrawProcessReq {
    /**
     * 用户ID
     */
    private String uId;

    /**
     * 活动ID
     */
    private Long activityId;

    public DrawProcessReq() {
    }

    public DrawProcessReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
