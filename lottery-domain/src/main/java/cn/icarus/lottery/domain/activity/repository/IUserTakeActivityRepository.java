package cn.icarus.lottery.domain.activity.repository;

import java.util.Date;

/**
 * @author Icarus
 * @description 用户参与活动仓储接口
 * @date 2023/4/2
 */
public interface IUserTakeActivityRepository {
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
    int subtractionLeftCount(Long activityId, String activityName, Integer takeCount, Integer userTakeLeftCount, String uId, Date partakeDate);


    /**
     * 参与活动
     *
     * @param activityId        活动Id
     * @param activityName      活动名称
     * @param takeCount         规定次数
     * @param userTakeLeftCount 用户剩余参与次数
     * @param uId               用户id
     * @param takeDate          参与时间
     * @param takeId            参与编号
     */
    void takeActivity(Long activityId,String activityName,Integer takeCount,Integer userTakeLeftCount,String uId,Date takeDate,Long takeId);

}
