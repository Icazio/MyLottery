package cn.icarus.lottery.interfaces;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.infrastructure.dao.IActivityDao;
import cn.icarus.lottery.infrastructure.po.Activity;
import cn.icarus.lottery.rpc.IActivityBooth;
import cn.icarus.lottery.rpc.dto.ActivityDto;
import cn.icarus.lottery.rpc.req.ActivityReq;
import cn.icarus.lottery.rpc.res.ActivityRes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description
 * @date 2023/3/20 15:41
 */
@Service
public class ActivityBooth implements IActivityBooth {
    @Resource
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {

            Activity activity=activityDao.queryActivityById(req.getActivityId());

        ActivityDto activityDto=new ActivityDto();
        activityDto.setActivityId(activity.getActivityId());
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());

        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()));
    }
}
