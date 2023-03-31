package cn.icarus.lottery.infrastructure.repository;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.activity.model.vo.*;
import cn.icarus.lottery.domain.activity.repository.IActivityRepository;
import cn.icarus.lottery.infrastructure.dao.IActivityDao;
import cn.icarus.lottery.infrastructure.dao.IAwardDao;
import cn.icarus.lottery.infrastructure.dao.IStrategyDao;
import cn.icarus.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.icarus.lottery.infrastructure.po.Activity;
import cn.icarus.lottery.infrastructure.po.Award;
import cn.icarus.lottery.infrastructure.po.Strategy;
import cn.icarus.lottery.infrastructure.po.StrategyDetail;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30 21:47
 */
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IActivityDao activityDao;
    @Resource
    private IAwardDao awardDao;
    @Resource
    private IStrategyDao strategyDao;
    @Resource
    private IStrategyDetailDao strategyDetailDao;
    @Override
    public void addActivity(ActivityVO activityVO) {
        Activity req=new Activity();
        BeanUtil.copyProperties(activityVO,req);
        activityDao.insert(req);
    }

    @Override
    public void addAward(List<AwardVO> awardVOList) {
        List<Award> req=new ArrayList<>();
        for(AwardVO awardVO:awardVOList){
            Award award=new Award();
            BeanUtil.copyProperties(awardVO,award);
            req.add(award);
        }
        awardDao.insertList(req);
    }

    @Override
    public void addStrategy(StrategyVO strategyVO) {
        Strategy req=new Strategy();
        BeanUtil.copyProperties(strategyVO,req);
        strategyDao.insert(req);
    }

    @Override
    public void addStrategyDetailList(List<StrategyDetailVO> strategyDetailVOList) {
        List<StrategyDetail> req=new ArrayList<>();
        for(StrategyDetailVO strategyDetailVO:strategyDetailVOList){
            StrategyDetail strategyDetail=new StrategyDetail();
            BeanUtil.copyProperties(strategyDetailVO,strategyDetail);
            req.add(strategyDetail);
        }
        strategyDetailDao.insertList(req);
    }

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState, Enum<Constants.ActivityState> afterState) {
        AlterStateVO alterStateVO = new AlterStateVO(activityId,((Constants.ActivityState) beforeState).getCode(),((Constants.ActivityState) afterState).getCode());
        int count = activityDao.alterState(alterStateVO);
        return count==1;

    }
}
