package cn.icarus.lottery.domain.activity.service.stateflow.event;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @author Icarus
 * @description
 *      ArraignmentState提审状态中的流程中
 *          待审状态不可重复提审
 *          非关闭活动不可开启
 *          代审核活动不可执行活动中变更
 *          而：审核通过、审核拒绝、撤销审核、活动关闭 都可以操作
 *    ●通过这样的设计模式结构，优化掉原本需要在各个流程节点中的转换使用if-else场景
 * @date 2023/3/30 14:44
 */
@Component
public class ArraignmentState extends AbstractState {
    /**【×】待审状态不可重复提审*/
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "待审状态不可重复提审");
    }

    /**【√】活动通过*/
    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess= activityRepository.alterStatus(activityId,currentState,Constants.ActivityState.PASS);
        return isSuccess?Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核通过完成"):Result.buildErrorResult("活动状态变更失败");
    }

    /**【√】活动拒绝*/
    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess= activityRepository.alterStatus(activityId,currentState,Constants.ActivityState.REFUSE);
        return isSuccess?Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核未通过"):Result.buildErrorResult("活动状态变更失败");
    }

    /**【√】撤审*/
    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess= activityRepository.alterStatus(activityId,currentState,Constants.ActivityState.EDIT);
        return isSuccess?Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核撤销回编辑中"):Result.buildErrorResult("活动状态变更失败");
    }

    /**【√】活动关闭*/
    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess= activityRepository.alterStatus(activityId,currentState,Constants.ActivityState.CLOSE);
        return isSuccess?Result.buildResult(Constants.ResponseCode.SUCCESS, "活动审核关闭"):Result.buildErrorResult("活动状态变更失败");
    }

    /**【×】非关闭活动不可开启*/
    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "非关闭活动不可开启");

    }

    /**【×】待审核活动不可执行活动中变更*/
    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "待审核活动不可执行活动中变更");

    }
}
