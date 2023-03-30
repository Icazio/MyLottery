package cn.icarus.lottery.domain.strategy.repository.impl;

import cn.icarus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.icarus.lottery.domain.strategy.repository.IStrategyRepository;
import cn.icarus.lottery.infrastructure.dao.IAwardDao;
import cn.icarus.lottery.infrastructure.dao.IStrategyDao;
import cn.icarus.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.icarus.lottery.infrastructure.po.Award;
import cn.icarus.lottery.infrastructure.po.Strategy;
import cn.icarus.lottery.infrastructure.po.StrategyDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/26 16:03
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;
    @Resource
    IStrategyDetailDao strategyDetailDao;
    @Resource
    private IAwardDao awardDao;


    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        Strategy strategy=strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList=strategyDetailDao.queryStrategyDetailList(strategyId);
        return new StrategyRich(strategyId,strategy,strategyDetailList);
    }

    @Override
    public Award queryAwardInfo(String awardId) {

        return awardDao.queryAwardInfo(awardId);
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req=new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);
        int count= strategyDetailDao.deductStock(req);
        return count==1;
    }


}
