package cn.icarus.lottery.domain.activity.service.stateflow;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30 14:44
 */
public interface IStateHandler {
    /**
     * 1.活动提审
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    Result arraignment(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 2.审核通过
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    Result checkPass(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 3.审核拒绝
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 4.撤审撤销
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 5.活动关闭
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    Result close(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 6.活动开启
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    Result open(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 7.活动执行
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    Result doing(Long activityId, Enum<Constants.ActivityState> currenState);






}
