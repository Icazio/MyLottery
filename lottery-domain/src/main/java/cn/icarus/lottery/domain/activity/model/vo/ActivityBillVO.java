package cn.icarus.lottery.domain.activity.model.vo;

import java.util.Date;

/**
 * @author Icarus
 * @description 活动账单【库存、状态、日期、个人参与次数】
 *      * 用户ID  uId;
 *      * 活动ID  Long activityId;
 *      * 活动名称 String activityName;
 *      * 开始时间 Date beginDateTime;
 *      * 结束时间 Date endDateTime;
 *      * 库存剩余 Integer stockSurplusCount;
 *      * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启
     Constants.ActivityState  Integer state;
 *      * 策略ID Long strategyId;
 *      * 每人可参与次数 Integer takeCount;
 *      * 已参加次数 Integer userTakeLeftCount;
 * @date 2023/4/2 18:53
 */
public class ActivityBillVO {
    /**
     * 用户ID uID
     */
    private String uId;
    /**
     * 活动ID activityId
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private  String activityName;
    /**
     * 开始时间
     */
    private Date beginDateTime;

    /**结束时间*/
    private Date endDateTime;

    /**库存剩余*/
    private Integer stockSurplusCount;
    /**活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启
     * Constants.ActivityState*/
    private Integer state;
    /**策略ID*/
    private Long strategyId;
    /**每人可参与次数*/
    private Integer takeCount;
    /**已参加次数*/
    private Integer userTakeLeftCount;

    @Override
    public String toString() {
        return "ActivityBillVO{" +
                "uId='" + uId + '\'' +
                ", activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", beginDateTime=" + beginDateTime +
                ", endDateTime=" + endDateTime +
                ", stockSurplusCount=" + stockSurplusCount +
                ", state=" + state +
                ", strategyId=" + strategyId +
                ", takeCount=" + takeCount +
                ", userTakeLeftCount=" + userTakeLeftCount +
                '}';
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getStockSurplusCount() {
        return stockSurplusCount;
    }

    public void setStockSurplusCount(Integer stockSurplusCount) {
        this.stockSurplusCount = stockSurplusCount;
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

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }

    public Integer getUserTakeLeftCount() {
        return userTakeLeftCount;
    }

    public void setUserTakeLeftCount(Integer userTakeLeftCount) {
        this.userTakeLeftCount = userTakeLeftCount;
    }
}
