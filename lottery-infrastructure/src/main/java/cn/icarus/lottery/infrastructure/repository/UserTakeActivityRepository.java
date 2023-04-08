package cn.icarus.lottery.infrastructure.repository;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.activity.model.vo.DrawOrderVO;
import cn.icarus.lottery.domain.activity.model.vo.UserTakeActivityVO;
import cn.icarus.lottery.domain.activity.repository.IUserTakeActivityRepository;
import cn.icarus.lottery.infrastructure.dao.IUserStrategyExportDao;
import cn.icarus.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import cn.icarus.lottery.infrastructure.dao.IUserTakeActivityDao;
import cn.icarus.lottery.infrastructure.po.UserStrategyExport;
import cn.icarus.lottery.infrastructure.po.UserTakeActivity;
import cn.icarus.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Icarus
 * @description
 * @date 2023/4/2 14:22
 */
@Repository
public class UserTakeActivityRepository implements IUserTakeActivityRepository {

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;
    @Resource
    private IUserStrategyExportDao userStrategyExportDao;


    @Override
    public UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setActivityId(activityId);
        UserTakeActivity noConsumedTakeActivityOrder = userTakeActivityDao.queryNoConsumedTakeActivityOrder(userTakeActivity);

        //如果没有查询到符合的领取单，直接返回NULL
        if (noConsumedTakeActivityOrder == null) {
            return null;
        }

        UserTakeActivityVO userTakeActivityVO = new UserTakeActivityVO();
        userTakeActivityVO.setActivityId(noConsumedTakeActivityOrder.getActivityId());
        userTakeActivityVO.setTakeId(noConsumedTakeActivityOrder.getTakeId());
        userTakeActivityVO.setStrategyId(noConsumedTakeActivityOrder.getStrategyId());
        userTakeActivityVO.setState(noConsumedTakeActivityOrder.getState());

        return userTakeActivityVO;
    }

    /**
     * 扣减个人活动参与次数
     *
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param takeCount         规定参与次数
     * @param userTakeLeftCount 剩余参与次数
     * @param uId               用户id
     * @param partakeDate       参与时间
     * @return 更新后的剩余活动参与次数
     */
    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate) {
        /*UserTakeActivityCount 每次参加都会创建一条信息*/
        //如果用户活动参与次数表是空的，就创建一个，并把该填的内容填进去，然后插到数据库里面去存起来,注意剩余次数是总次数减一
        if (userTakeLeftCount == null) {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount - 1);
            userTakeActivityCountDao.insert(userTakeActivityCount);

            return 1;
        } else {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            //将这条记录直接用于更新
            return userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }
    }

    /**
     * 领取活动
     *
     * @param activityId        活动Id
     * @param activityName      活动名称
     * @param takeCount         规定次数
     * @param userTakeLeftCount 用户剩余参与次数
     * @param uId               用户id
     * @param takeDate          参与时间
     * @param takeId            参与标号
     */
    @Override
    public void takeActivity(Long activityId, String activityName, Long strategyId,Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setTakeDate(takeDate);
        userTakeActivity.setActivityName(activityName);
        if (userTakeLeftCount == null) {
            userTakeActivity.setTakenCount(1);
        } else {
            userTakeActivity.setTakenCount(takeCount - userTakeLeftCount+1);
        }
        userTakeActivity.setStrategyId(strategyId);
        userTakeActivity.setState(Constants.TakeState.NO_USED.getCode());
        String uuid = uId + "_" + activityId + "_" + userTakeActivity.getTakenCount();
        userTakeActivity.setUuid(uuid);

        userTakeActivityDao.insert(userTakeActivity);
    }

    /**锁定活动领取记录【方法：将state从0改成1--说明已经完成了，于是上锁】*/
    @Override
    public int lockTackActivity(String uId, Long activityId, Long takeId) {
        UserTakeActivity userTakeActivity=new UserTakeActivity();
        userTakeActivity.setuId((uId));
        userTakeActivity.setActivityId(activityId);
        //userTakeActivity.setTakeId(takeId);

        return userTakeActivityDao.lockTackActivity(userTakeActivity);
    }

    @Override
    public void saveUserStrategyExport(DrawOrderVO drawOrderVO) {
        UserStrategyExport userStrategyExport=new UserStrategyExport();
        userStrategyExport.setuId(drawOrderVO.getuId());
        userStrategyExport.setActivityId(drawOrderVO.getActivityId());
        userStrategyExport.setOrderId(drawOrderVO.getOrderId());
        userStrategyExport.setStrategyId(drawOrderVO.getStrategyId());
        userStrategyExport.setStrategyMode(drawOrderVO.getStrategyMode());
        userStrategyExport.setGrantDate(drawOrderVO.getGrantDate());
        userStrategyExport.setGrantType(drawOrderVO.getGrantType());
        userStrategyExport.setGrantState(drawOrderVO.getGrantState());
        userStrategyExport.setAwardId(drawOrderVO.getAwardId());
        userStrategyExport.setAwardType(drawOrderVO.getAwardType());
        userStrategyExport.setAwardName(drawOrderVO.getAwardName());
        userStrategyExport.setAwardContent(drawOrderVO.getAwardContent());
        userStrategyExport.setUuid(String.valueOf(drawOrderVO.getOrderId()));

        userStrategyExportDao.insert(userStrategyExport);

    }




}