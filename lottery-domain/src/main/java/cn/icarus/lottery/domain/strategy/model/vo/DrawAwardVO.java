package cn.icarus.lottery.domain.strategy.model.vo;

import java.util.Date;

/**
 * @author Icarus
 * @description 【用户ID、奖品ID、奖品类型、奖品名称、奖品内容、策略方式、发放奖品方式、发奖时间】
 * @date 2023/4/9 14:55
 */
public class DrawAwardVO {
    /**用户ID*/
    private String uId;
    /**奖品ID*/
    private String awardId;
    /**奖品类型*/
    private Integer awardType;
    /**奖品名称*/
    private  String awardName;
    /**奖品内容*/
    private String awardContent;
    /**策略方式*/
    private Integer strategyMode;
    /**发放奖品方式*/
    private Integer grantType;
    /**发奖时间*/
    private Date grantDate;
    public DrawAwardVO() {
    }

    public DrawAwardVO(String uId, String awardId, Integer awardType, String awardName, String awardContent) {
        this.uId = uId;
        this.awardId = awardId;
        this.awardType = awardType;
        this.awardName = awardName;
        this.awardContent = awardContent;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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
        return "DrawAwardVO{" +
                "uId='" + uId + '\'' +
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
