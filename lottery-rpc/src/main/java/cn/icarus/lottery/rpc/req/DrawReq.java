package cn.icarus.lottery.rpc.req;

import java.io.Serializable;

/**
 * @author Icarus
 * @description 普通【非量化】抽奖请求【用户ID、活动ID】
 * @date 2023/4/9 10:24
 */
public class DrawReq implements Serializable {
    /**用户ID*/
    private String uId;
    /**活动ID*/
    private Long activityId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long activityId) {
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
