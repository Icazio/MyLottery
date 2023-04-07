package cn.icarus.lottery.domain.activity.service.partake.impl;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.activity.model.req.PartakeReq;
import cn.icarus.lottery.domain.activity.model.vo.ActivityBillVO;
import cn.icarus.lottery.domain.activity.model.vo.DrawOrderVO;
import cn.icarus.lottery.domain.activity.model.vo.UserTakeActivityVO;
import cn.icarus.lottery.domain.activity.repository.IUserTakeActivityRepository;
import cn.icarus.lottery.domain.activity.service.partake.BaseActivityPartake;
import cn.icarus.lottery.domain.support.ids.IIdGenerator;
import cn.icarus.middleware.db.router.strategy.IDBRouterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Icarus
 * @description
 * @date 2023/4/2 21:33
 */
@Service(value="activityPartake")
public class ActivityPartakeImpl extends BaseActivityPartake {


    private Logger logger= LoggerFactory.getLogger(ActivityPartakeImpl.class);

    @Resource
    private IDBRouterStrategy dbRouter;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private IUserTakeActivityRepository userTakeActivityRepository;
    /**所需要的ID类型和它对应的生成器*/
    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }

    @Override
    protected Result checkActivityBill(PartakeReq partake, ActivityBillVO bill) {
        //校验：活动状态
        //如果活动账单的状态不是正在活动中
        if(!Constants.ActivityState.DOING.getCode().equals(bill.getState())) {
            //将活动当前的状态记录到日志中
            logger.warn("活动当前状态非可用，state:{}",bill.getState());
            //返回结果
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR,"活动当前状态非可用");
        }
        //校验：活动日期
        //如果活动开始日期在用户参加活动生成之后或者活动结束时间在用户参与活动账单之前
        if(bill.getBeginDateTime().after(partake.getPartakeDate())||bill.getEndDateTime().before(partake.getPartakeDate())){
            //那么活动时间范围不可用，将活动当前的时间记录到日志中
            logger.warn("活动时间范围非可用 beginDateTime：{} endDateTime：{}", bill.getBeginDateTime(), bill.getEndDateTime());
            //返回结果
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动时间范围非可用");
        }

        // 校验：活动库存
        //如果活动库存小于等于0
        if (bill.getStockSurplusCount() <= 0) {
            //那么活动剩余库存非可用，将活动当前的剩余库存记录到日志中
            logger.warn("活动剩余库存非可用 stockSurplusCount：{}", bill.getStockSurplusCount());
            //返回结果
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "活动剩余库存非可用");
        }

        // 校验：个人库存 - 个人活动剩余可领取次数
        //如果个人活动剩余可领取次数小于等于0
        if (null != bill.getUserTakeLeftCount() && bill.getUserTakeLeftCount() <= 0) {
            //那么个人领取次数非可用，将活动当前的个人活动剩余可领取次数存记录到日志中
            logger.warn("个人领取次数非可用 userTakeLeftCount：{}", bill.getUserTakeLeftCount());
            //返回结果
            return Result.buildResult(Constants.ResponseCode.UNKNOWN_ERROR, "个人领取次数非可用");
        }

        return Result.buildSuccessResult();
    }

    @Override
    protected Result subtractionActivityStock(PartakeReq req) {
        //去修改数据库中的活动库存,并得到新的活动库存
        int count = activityRepository.subtractionActivityStock(req.getActivityId());
        //如果没有matched,也就是说修改失败了的话
        if (0 == count) {
            //将错误信息和活动的id记录到日志中
            logger.error("扣减活动库存失败 activityId：{}", req.getActivityId());
            return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
        }
        return Result.buildSuccessResult();
    }


    /**
     * 获取参与单号:用雪花算法生成器生成一个单号
     * 将这个参与的这次记录【ActivityId(), getActivityName(),TakeCount(), UserTakeLeftCount, uId，PartakeDate, takeId】插入user_take_activity表中
     * @param partake 参与活动请求
     * @param bill     活动账单
     * @return Result.buildSuccessResult
     */
    @Override
    protected Result grabActivity(PartakeReq partake, ActivityBillVO bill) {
        try {
            //将用户ID传到dbRouter中
            dbRouter.doRouter(partake.getuId());
            //事务
            return transactionTemplate.execute(status -> {
                try {
                    // 扣减个人已参与次数
                    int updateCount = userTakeActivityRepository.subtractionLeftCount(bill.getActivityId(), bill.getActivityName(), bill.getTakeCount(), bill.getUserTakeLeftCount(), partake.getuId(), partake.getPartakeDate());
                    //如果扣减失败
                    if (0 == updateCount) {
                        //回滚
                        status.setRollbackOnly();
                        //将错误信息和活动的id、用户id记录到日志中
                        logger.error("领取活动，扣减个人已参与次数失败 activityId：{} uId：{}", partake.getActivityId(), partake.getuId());
                        //返回结果
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }

                    // 插入领取活动信息
                    //获取参与的单号:得到了雪花算法的生成器，用雪花算法生成器生成一个单号
                    Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
                    //将这个参与的这次记录插入user_take_activity表中
                    userTakeActivityRepository.takeActivity(bill.getActivityId(), bill.getActivityName(), bill.getStrategyId(), bill.getTakeCount(), bill.getUserTakeLeftCount(), partake.getuId(), partake.getPartakeDate(), takeId);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("领取活动，唯一索引冲突 activityId：{} uId：{}", partake.getActivityId(), partake.getuId(), e);
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        } finally {
                dbRouter.clear();
        }
    }


    @Override
    public Result recordDrawOrder(DrawOrderVO drawOrder) {
        try {
            dbRouter.doRouter(drawOrder.getuId());
            return transactionTemplate.execute(status -> {
                try {
                    //锁定活动领取记录【方法：将state从0改成1--说明已经完成了，于是上锁】
                    int lockCount = userTakeActivityRepository.lockTackActivity(drawOrder.getuId(),drawOrder.getActivityId(), drawOrder.getTakeId());
                    if (0 == lockCount) {
                        status.setRollbackOnly();
                        logger.error("记录中奖单，个人参与活动抽奖已消耗完 activityId:{} uId:{}", drawOrder.getActivityId(), drawOrder.getuId());
                        return Result.buildResult(Constants.ResponseCode.NO_UPDATE);
                    }
                    //存到user_take_export里
                    userTakeActivityRepository.saveUserStrategyExport(drawOrder);
                } catch (DuplicateKeyException e) {
                    status.setRollbackOnly();
                    logger.error("记录中奖单，唯一索引冲突 activityId：{} uId：{}", drawOrder.getActivityId(), drawOrder.getuId());
                    return Result.buildResult(Constants.ResponseCode.INDEX_DUP);
                }
                return Result.buildSuccessResult();
            });
        }finally {
            dbRouter.clear();
        }
    }
}
