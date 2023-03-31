package cn.icarus.lottery.domain.strategy.model.vo;

import java.math.BigDecimal;

/**
 * @author Icarus
 * @description  策略明细
 * @date 2023/3/26
 */
public class StrategyDetailBriefVO {

    /**自增ID*/
    private String id;

    /**策略ID*/
    private Long strategyId;

    /**奖品ID*/
    private String awardId;

    /**奖品名称*/
    private String awardName;

    /**奖品数量*/
    private Integer awardCount;

    /**奖品剩余数量*/
    private Integer awardSurplusCount;

    /**中奖概率*/
    private BigDecimal awardRate;

    /**创建时间*/
    private String createTime;

    /** 修改时间*/
    private String updateTime;

    public String getAwardName() {
        return awardName;
    }

    public Integer getAwardCount() {
        return awardCount;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public void setAwardCount(Integer awardCount) {
        this.awardCount = awardCount;
    }

    public Integer getAwardSurplusCount() {
        return awardSurplusCount;
    }

    public void setAwardSurplusCount(Integer awardSurplusCount) {
        this.awardSurplusCount = awardSurplusCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }


    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
