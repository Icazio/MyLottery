package cn.icarus.lottery.domain.activity.repository;

import cn.icarus.lottery.domain.activity.model.vo.DrawOrderVO;
import cn.icarus.lottery.domain.activity.model.vo.UserTakeActivityVO;

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
     * 领取活动
     *
     * @param activityId        活动Id
     * @param activityName      活动名称
     * @param strategyId        抽奖策略ID
     * @param takeCount         规定次数
     * @param userTakeLeftCount 用户剩余参与次数
     * @param uId               用户id
     * @param takeDate          参与时间
     * @param takeId            参与编号
     */
    void takeActivity(Long activityId,String activityName,Long strategyId,Integer takeCount,Integer userTakeLeftCount,String uId,Date takeDate,Long takeId);

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * @param activityId    活动id
     * @param uId           用户id
     * @return  【活动id、用户id、策略id、参与单号】
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);


    /**
     * 【环节：结果落库，导入user_take_export】锁定活动领取记录
     * @param uId        用户ID
     * @param activityOd 活动ID
     * @param takeId     领取ID
     * @return          更新结果
     */
    int lockTackActivity(String uId,Long activityOd,Long takeId);


    /**
     * 【环节：结果落库，导入user_take_export】保存抽奖信息
     * @param drawOrderVO   中奖单
     */
    void saveUserStrategyExport(DrawOrderVO drawOrderVO);







}
