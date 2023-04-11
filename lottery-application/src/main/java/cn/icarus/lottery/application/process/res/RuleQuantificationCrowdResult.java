package cn.icarus.lottery.application.process.res;

import cn.icarus.lottery.common.Result;

/**
 * @author Icarus
 * @description
 * @date 2023/4/9 20:12
 */
public class RuleQuantificationCrowdResult extends Result {

    /**活动ID*/
    private Long activityId;

    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId(){return activityId;}

    public void setActivityId(Long activityId){
        this.activityId=activityId;
    }
}
