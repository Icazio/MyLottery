package cn.icarus.lottery.domain.activity.model.req;

import cn.icarus.lottery.domain.activity.model.aggregate.ActivityConfigRich;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30 16:29
 */
public class ActivityConfigReq {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动配置信息
     */
    private ActivityConfigRich activityConfigRich;

    public ActivityConfigReq() {
    }

    public ActivityConfigReq(Long activityId, ActivityConfigRich activityConfigRich) {
        this.activityId = activityId;
        this.activityConfigRich = activityConfigRich;
    }

    public ActivityConfigRich getActivityConfigRich() {
        return activityConfigRich;
    }

    public void setActivityConfigRich(ActivityConfigRich activityConfigRich) {
        this.activityConfigRich = activityConfigRich;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}
