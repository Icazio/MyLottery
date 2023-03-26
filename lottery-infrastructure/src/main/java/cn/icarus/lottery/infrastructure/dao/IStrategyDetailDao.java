package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.infrastructure.po.Strategy;
import cn.icarus.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStrategyDetailDao {
    List<StrategyDetail> queryStrategyDetailInfo(Long strategyId);
}
