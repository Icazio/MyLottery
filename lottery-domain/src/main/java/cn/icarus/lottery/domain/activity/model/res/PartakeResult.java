package cn.icarus.lottery.domain.activity.model.res;

import cn.icarus.lottery.common.Result;

/**
 * @author Icarus
 * @description 活动参与结果【策略ID、活动单号】
 * @date 2023/4/2 18:41
 */
public class PartakeResult extends Result {
    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 活动领取ID
     */
    private Long takeId;



    public PartakeResult(String code,String info) {
        super(code,info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }
}
