package cn.icarus.lottery.rpc.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Icarus
 * @description 奖品信息【用户ID、活动ID、奖品ID、品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）、奖品名称、发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）、发奖时间】
 * @date 2023/4/9 10:32
 */
public class AwardDTO implements Serializable {
    /**用户ID*/
    private String userId;
    /**活动ID*/
    private Long activityId;
    /**奖品ID*/
    private String awardId;
    /**奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）*/
    private Integer awardType;
    /**奖品名称*/
    private String awardName;
    /**奖品内容「描述、奖品码、sku」*/
    private String awardContent;
    /**策略方式（1:单项概率、2:总体概率）*/
    private Integer strategyMode;
    /**发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）*/
    private Integer grantType;
    /**发奖时间*/
    private Date grantDate;


    public AwardDTO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Integer getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    public Integer getGrantType() {
        return grantType;
    }

    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    @Override
    public String toString() {
        return "AwardDTO{" +
                "userId='" + userId + '\'' +
                ", activityId=" + activityId +
                ", awardId='" + awardId + '\'' +
                ", awardType=" + awardType +
                ", awardName='" + awardName + '\'' +
                ", awardContent='" + awardContent + '\'' +
                ", strategyMode=" + strategyMode +
                ", grantType=" + grantType +
                ", grantDate=" + grantDate +
                '}';
    }
}
