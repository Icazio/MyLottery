package cn.icarus.lottery.infrastructure.repository;

import cn.icarus.lottery.domain.activity.repository.IUserTakeActivityRepository;
import cn.icarus.lottery.infrastructure.dao.IUserTakeActivityDao;
import cn.icarus.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import cn.icarus.lottery.infrastructure.po.UserTakeActivity;
import cn.icarus.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Icarus
 * @description
 * @date 2023/4/2 14:22
 */
@Component
public class UserTakeActivityRepository implements IUserTakeActivityRepository {

    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao userTakeActivityDao;


    /**
     * 扣减个人活动参与次数
     *
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param takeCount         规定参与次数
     * @param userTakeLeftCount 剩余参与次数
     * @param uId               用户id
     * @param partakeDate       参与时间
     * @return                  更新后的剩余活动参与次数
     */
    @Override
    public int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate) {
        /*UserTakeActivityCount 每次参加都会创建一条信息*/
        //如果用户活动参与次数表是空的，就创建一个，并把该填的内容填进去，然后插到数据库里面去存起来,注意剩余次数是总次数减一
        if(userTakeLeftCount==null){
            UserTakeActivityCount userTakeActivityCount=new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount-1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            //第一次参加，参加次数 1
            return  1;
        } else{
            UserTakeActivityCount userTakeActivityCount=new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            //将这条记录直接用于更新
            return userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }
    }

    /**
     * 参与活动
     * @param activityId        活动Id
     * @param activityName      活动名称
     * @param takeCount         规定次数
     * @param userTakeLeftCount 用户剩余参与次数
     * @param uId               用户id
     * @param takeDate          参与时间
     * @param takeId            参与标号
     */
    @Override
    public void takeActivity(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId) {
        UserTakeActivity userTakeActivity=new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setTakeDate(takeDate);
        userTakeActivity.setActivityName(activityName);
        if(userTakeLeftCount==null){
            userTakeActivity.setTakeCount(1);
        }else{
            userTakeActivity.setTakeCount(takeCount-userTakeLeftCount);
        }
        String uuid=uId+"_"+activityId+"_"+userTakeActivity.getTakeCount();
        userTakeActivity.setUuid(uuid);

        userTakeActivityDao.insert(userTakeActivity);
    }
}
