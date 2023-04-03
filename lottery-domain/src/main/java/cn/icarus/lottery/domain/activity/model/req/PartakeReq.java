package cn.icarus.lottery.domain.activity.model.req;

import java.util.Date;

/**
 * @author Icarus
 * @description 参与活动请求
 *      用户ID uId
 *      活动ID activityId
        活动参与时间 partakeDate
 * @date 2023/4/2 18:42
 */
public class PartakeReq {
    /**
     * 用户ID uId
     */
    private String  uId;
    /**
     * 活动ID activityId
     */
    private Long activityId;
    /**
     * 活动参与时间 partakeDate
     */
    private Date partakeDate;

    public PartakeReq(String uId, Long activityId) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate=new Date();
    }

    public PartakeReq(String uId, Long activityId, Date partakeDate) {
        this.uId = uId;
        this.activityId = activityId;
        this.partakeDate = partakeDate;
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

    public Date getPartakeDate() {
        return partakeDate;
    }

    public void setPartakeDate(Date partakeDate) {
        this.partakeDate = partakeDate;
    }
}
