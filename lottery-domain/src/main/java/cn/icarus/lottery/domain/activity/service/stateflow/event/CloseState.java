package cn.icarus.lottery.domain.activity.service.stateflow.event;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.activity.service.stateflow.AbstractState;
import org.springframework.stereotype.Component;

/**
 * @author Icarus
 * @description 活动关闭状态
 * @date 2023/3/30 14:44
 */
@Component
public class CloseState extends AbstractState {
    /**【×】关闭状态不可提审*/
    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currenState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动关闭不可提审");
    }

    /**【×】关闭状态无法通过*/
    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currenState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动关闭不可重复关闭");

    }
    /**【×】关闭状态无法拒绝*/
    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currenState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动关闭不可审核通过");
    }

    /**【×】关闭状态无法撤审*/
    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currenState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动关闭不可审核拒绝");
    }

    /**【×】关闭状态无法重复关闭*/
    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currenState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动关闭不可撤销审核");
    }

    /**【√】活动开启*/
    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currenState) {
        boolean isSuccess=activityRepository.alterStatus(activityId,currenState,Constants.ActivityState.OPEN);
        return isSuccess? Result.buildResult(Constants.ResponseCode.SUCCESS, "活动开启成功"):Result.buildErrorResult("活动状态变更失败");
    }

    /**【×】关闭状态无法执行活动*/
    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currenState) {
        return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动关闭不可变更活动中");
    }
}
