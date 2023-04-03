package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.infrastructure.po.UserStrategyExport;
import cn.icarus.middleware.db.router.annotation.DBRouter;
import cn.icarus.middleware.db.router.annotation.DBRouterStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Icarus
 * @description 用户策略计算结果表DAO
 * @date 2023/4/2
 */
@Mapper
@DBRouterStrategy(spiltTable = true)
public interface IUserStrategyExportDao {
    /**
     * 新增数据
     * @param userStrategyExport    用户策略
     */
    @DBRouter(key="uId")
    void insert(UserStrategyExport userStrategyExport);

    /**
     * 查询数据
     * @param uId   用户ID
     * @return  用户策略
     */
    @DBRouter
    UserStrategyExport queryUserStrategyExportByUId(String uId);

}
