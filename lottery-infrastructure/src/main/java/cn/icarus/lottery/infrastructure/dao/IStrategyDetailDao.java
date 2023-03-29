package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/26
 */
@Mapper
public interface IStrategyDetailDao {
    /**
     * 查询策略表详细配置
     * @param strategyId    策略ID
     * @return              返回结果
     */
    List<StrategyDetail> queryStrategyDetailInfo(Long strategyId);

    /**
     * 查询无库存策略奖品ID
     * @param strategyId    策略ID
     * @return              返回结果
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyDetailReq 策略ID、奖品ID
     * @return                  返回结果
     */
    int deductStock(StrategyDetail strategyDetailReq);
}
