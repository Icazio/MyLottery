package cn.icarus.lottery.domain.activity.service.stateflow.impl;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.activity.service.stateflow.IStateHandler;
import cn.icarus.lottery.domain.activity.service.stateflow.StateConfig;
import org.springframework.stereotype.Service;

/**
 * @author Icarus
 * @description 实现状态处理服务
 *      通过在状态组获取对应的状态处理服务和操作变更状态
 * @date 2023/3/30 14:44
 */

@Service
public class StateHandlerImpl extends StateConfig implements IStateHandler {

    @Override
    public Result arraignment(Long activityId, Enum<Constants.ActivityState> currenState) {
        return stateGroup.get(currenState).arraignment(activityId,currenState);
    }

    @Override
    public Result checkPass(Long activityId, Enum<Constants.ActivityState> currenState) {
        return stateGroup.get(currenState).checkPass(activityId,currenState);

    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currenState) {
        return stateGroup.get(currenState).checkRefuse(activityId,currenState);

    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currenState) {
        return stateGroup.get(currenState).checkRevoke(activityId,currenState);

    }

    @Override
    public Result close(Long activityId, Enum<Constants.ActivityState> currenState) {
        return stateGroup.get(currenState).close(activityId,currenState);

    }

    @Override
    public Result open(Long activityId, Enum<Constants.ActivityState> currenState) {
        return stateGroup.get(currenState).open(activityId,currenState);

    }

    @Override
    public Result doing(Long activityId, Enum<Constants.ActivityState> currenState) {
        return stateGroup.get(currenState).doing(activityId,currenState);

    }
}
