package cn.icarus.lottery.infrastructure.repository;

import cn.icarus.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.icarus.lottery.domain.strategy.model.vo.AwardBriefVO;
import cn.icarus.lottery.domain.strategy.model.vo.StrategyBriefVO;
import cn.icarus.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import cn.icarus.lottery.domain.strategy.repository.IStrategyRepository;
import cn.icarus.lottery.infrastructure.dao.IAwardDao;
import cn.icarus.lottery.infrastructure.dao.IStrategyDao;
import cn.icarus.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.icarus.lottery.infrastructure.po.Strategy;
import cn.icarus.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }

        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }
    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {

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
