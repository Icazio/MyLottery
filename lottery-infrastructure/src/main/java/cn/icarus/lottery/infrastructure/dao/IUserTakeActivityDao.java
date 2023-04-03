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
    @DBRouter(key="uId")
    void insert(UserTakeActivity userTakeActivity);
}
