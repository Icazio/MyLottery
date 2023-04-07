package cn.icarus.lottery.infrastructure.dao;


import cn.icarus.lottery.infrastructure.po.UserTakeActivity;
import cn.icarus.middleware.db.router.annotation.DBRouter;
import cn.icarus.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author Icarus
 * @description 用户参加活动表DAO（对应mybatisMapper:UserTakeActivity）
 *      自增ID Long id;
        用户ID String uId;
        活动参加编号 Long takeId;
        活动ID Long activityId;
        活动名称 String activityName;
        活动参与时间 Date takeDate;
        活动可参与次数 Integer takeCount;
        防重ID String uuid;
        创建时间 Date createTime;
        更新时间 Date updateTime;
 * @date 2023/4/2
 */
@Mapper
@DBRouterStrategy
public interface IUserTakeActivityDao {

    /**
     * 插入用户参加活动信息
     * @param userTakeActivity
     */
    @DBRouter
    void insert(UserTakeActivity userTakeActivity);

    /**
     * 查询是否存在未执行抽奖领取活动单【user_take_activity 存在 state = 0，领取了但抽奖过程失败的，可以直接返回领取结果继续抽奖】
     * 查询此活动ID，用户最早领取但未消费的一条记录【这部分一般会有业务流程限制，比如是否处理最先还是最新领取单，要根据自己的业务实际场景进行处理】
     *
     * @param userTakeActivity 请求入参
     * @return                 领取结果
     */
    @DBRouter
    UserTakeActivity queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivity);

    /**
     * 锁定活动领取记录【方法：将state从0改成1--说明已经完成了，于是上锁】
     *
     * @param userTakeActivity  入参
     * @return                  更新结果
     */
    int lockTackActivity(UserTakeActivity userTakeActivity);

}
