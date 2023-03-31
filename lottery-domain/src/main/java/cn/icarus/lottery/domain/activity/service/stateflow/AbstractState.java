package cn.icarus.lottery.domain.activity.service.stateflow;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @author Icarus
 * @description 在整个接口中提供各项状态流转服务的接口。7个方法
 *              所有的方法的入参的是一样的--activityId，currentStatus
 *              只有他们的具体实现是不同的
 * @date 2023/3/30 14:44
 */
public abstract class AbstractState {
    @Resource
    protected IActivityRepository activityRepository;

    /**
     * 1.活动提审
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    public abstract Result arraignment(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 2.审核通过
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    public abstract Result checkPass(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 3.审核拒绝
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    public abstract Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 4.撤审撤销
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    public abstract Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 5.活动关闭
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    public abstract Result close(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 6.活动开启
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    public abstract Result open(Long activityId, Enum<Constants.ActivityState> currenState);

    /**
     * 7.活动执行
     * @param activityId   活动ID
     * @param currenState  当前状态
     * @return             执行结果
     */
    public abstract Result doing(Long activityId, Enum<Constants.ActivityState> currenState);






}

