package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.infrastructure.po.UserTakeActivityCount;
import cn.icarus.middleware.db.router.annotation.DBRouter;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Icarus
 * @description
 * @date 2023/4/2 17:12
 */
@Mapper
public interface IUserTakeActivityCountDao {
    /**
     * 向数据库用户活动参与表插入这条新创建的数据。
     * @param userTakeActivityCount 用户活动参与表
     */
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     *更新剩余次数
     * @param userTakeActivityCount 用户活动参与次数表
     * @return  总共参与次数
     */
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);


    /**
     * 查询用户领取次数
     * @param userTakeActivityCountReq 请求入参【活动号、用户ID】
     * @return 领取结果【自增id、用户id、活动号、可领取次数、已领取次数、时间】
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);


}
