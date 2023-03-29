package cn.icarus.lottery.domain.strategy.model.vo;

import java.math.BigDecimal;

/**
 * @author Icarus
 * @description
 * @date 2023/3/25 12:21
 */
public class AwardRateInfo {

    private String awardId;
    private BigDecimal awardRate;

    public AwardRateInfo() {
    }

    public AwardRateInfo(String awardId, BigDecimal awardRate) {
        this.awardId = awardId;
        this.awardRate = awardRate;
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
}
