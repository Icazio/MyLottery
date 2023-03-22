package cn.icarus.lottery.interfaces;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.infrastructure.dao.IActivityDao;
import cn.icarus.lottery.infrastructure.po.Activity;
import cn.icarus.lottery.rpc.IActivityBooth;
import cn.icarus.lottery.rpc.dto.ActivityDto;
import cn.icarus.lottery.rpc.req.ActivityReq;
import cn.icarus.lottery.rpc.res.ActivityRes;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @description
 * @date 2023/3/20 15:41
 */
@Service // * 该注解表示该类是一个服务类，可以被Spring容器自动扫描并注入到其他需要使用该类的地方。
public class ActivityBooth implements IActivityBooth {
    @Resource //该注解表示在Spring容器中查找名为IActivityDao的Bean，并将其注入到当前类中的activityDao成员变量中。
    private IActivityDao activityDao;

    @Override
    public ActivityRes queryActivityById(ActivityReq req) {// 声明了一个公共方法queryActivityById，它接受一个ActivityReq类型的参数req，并返回一个ActivityRes类型的对象。
        Activity activity=activityDao.queryActivityById(req.getActivityId());// 从activityDao中查询一个Activity对象，根据传入的参数req.getActivityId()，获得该id对应的Activity实例。

        ActivityDto activityDto=new ActivityDto();// *声明一个新的ActivityDto实例。
        activityDto.setActivityId(activity.getActivityId());// 将查询到的Activity实例中的activityId属性设置给ActivityDto实例的activityId属性。
        activityDto.setActivityName(activity.getActivityName());
        activityDto.setActivityDesc(activity.getActivityDesc());
        activityDto.setBeginDateTime(activity.getBeginDateTime());
        activityDto.setEndDateTime(activity.getEndDateTime());
        activityDto.setStockCount(activity.getStockCount());
        activityDto.setTakeCount(activity.getTakeCount());
        return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()));// 返回一个新的ActivityRes实例，其中包含一个Result实例，该Result实例表示操作成功，并且将ActivityRes实例作为其构造函数的参数。

    }
}
/**
 * 这段代码是一个Java类，通过@Service注解声明为Spring容器中的一个服务类，并实现了IActivityBooth接口。以下是逐行解释：
 *
 * @Service
 * 该注解表示该类是一个服务类，可以被Spring容器自动扫描并注入到其他需要使用该类的地方。
 *
 * public class ActivityBooth implements IActivityBooth {
 * 定义了一个名为ActivityBooth的公共类，实现了IActivityBooth接口。
 *
 * @Resource
 * 该注解表示在Spring容器中查找名为IActivityDao的Bean，并将其注入到当前类中的activityDao成员变量中。
 *
 * private IActivityDao activityDao;
 * 声明了一个私有的IActivityDao类型的成员变量activityDao。
 *
 * @Override
 * 声明了当前方法将重写接口IActivityBooth中的queryActivityById方法。
 *
 * public ActivityRes queryActivityById(ActivityReq req) {
 * 声明了一个公共方法queryActivityById，它接受一个ActivityReq类型的参数req，并返回一个ActivityRes类型的对象。
 *
 * Activity activity = activityDao.queryActivityById(req.getActivityId());
 * 从activityDao中查询一个Activity对象，根据传入的参数req.getActivityId()，获得该id对应的Activity实例。
 *
 * ActivityDto activityDto = new ActivityDto();
 * 声明一个新的ActivityDto实例。
 *
 * activityDto.setActivityId(activity.getActivityId());
 * 将查询到的Activity实例中的activityId属性设置给ActivityDto实例的activityId属性。
 *
 * activityDto.setActivityName(activity.getActivityName());
 * 将查询到的Activity实例中的activityName属性设置给ActivityDto实例的activityName属性。
 *
 * activityDto.setActivityDesc(activity.getActivityDesc());
 * 将查询到的Activity实例中的activityDesc属性设置给ActivityDto实例的activityDesc属性。
 *
 * activityDto.setBeginDateTime(activity.getBeginDateTime());
 * 将查询到的Activity实例中的beginDateTime属性设置给ActivityDto实例的beginDateTime属性。
 *
 * activityDto.setEndDateTime(activity.getEndDateTime());
 * 将查询到的Activity实例中的endDateTime属性设置给ActivityDto实例的endDateTime属性。
 *
 * activityDto.setStockCount(activity.getStockCount());
 * 将查询到的Activity实例中的stockCount属性设置给ActivityDto实例的stockCount属性。
 *
 * activityDto.setTakeCount(activity.getTakeCount());
 * 将查询到的Activity实例中的takeCount属性设置给ActivityDto实例的takeCount属性。
 *
 * return new ActivityRes(new Result(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo()));
 * 返回一个新的ActivityRes实例，其中包含一个Result实例，该Result实例表示操作成功，并且将ActivityRes实例作为其构造函数的参数。
 */