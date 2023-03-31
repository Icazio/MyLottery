package cn.icarus.lottery.domain.activity.service.stateflow.event;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @author Icarus
 * @description 运行(活动中)状态
 * @date 2023/3/30 14:44
 */
@Component
public class DoingState extends AbstractState {
    /**【×】活动中不可提审*/
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动中不可提审");
    }

    /**【×】活动中不可审核通过*/
    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动中不可审核通过");
    }

    /**【×】活动中不可审核拒绝*/
    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动中不可审核拒绝");
    }

    /**【×】活动中不可撤销审核*/
    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动中不可撤销审核");
    }

    /**【√】活动关闭*/
    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currentState) {
        boolean isSuccess= activityRepository.alterStatus(activityId,currentState,Constants.ActivityState.CLOSE);
        return isSuccess?Result.buildResult(Constants.ResponseCode.SUCCESS, "活动关闭成功"):Result.buildErrorResult("活动状态变更失败");
    }

    /**【×】活动中不可开启*/
    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动中不可开启");
    }

    /**【×】活动中不可重复执行*/
    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currentState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动中不可重复执行");
    }
}
