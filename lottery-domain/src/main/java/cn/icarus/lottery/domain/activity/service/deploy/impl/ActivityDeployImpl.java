package cn.icarus.lottery.domain.activity.service.deploy.impl;

import cn.icarus.lottery.domain.activity.model.aggregate.ActivityConfigRich;
import cn.icarus.lottery.domain.activity.model.req.ActivityConfigReq;
import cn.icarus.lottery.domain.activity.model.vo.ActivityVO;
import cn.icarus.lottery.domain.activity.model.vo.AwardVO;
import cn.icarus.lottery.domain.activity.model.vo.StrategyDetailVO;
import cn.icarus.lottery.domain.activity.model.vo.StrategyVO;
import cn.icarus.lottery.domain.activity.repository.IActivityRepository;
import cn.icarus.lottery.domain.activity.service.deploy.IActivityDeploy;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30 16:19
 */
@Service
public class ActivityDeployImpl implements IActivityDeploy {
    private Logger logger= LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createActivity(ActivityConfigReq req) {
        logger.info("创建活动配置开始：activityId:{}",req.getActivityId());

        ActivityConfigRich activityConfigRich=req.getActivityConfigRich();
        try{
            //添加活动配置
            ActivityVO activityVO=activityConfigRich.getActivityVO();
            activityRepository.addActivity(activityVO);
            //添加奖品配置
            List<AwardVO> awardVOList=activityConfigRich.getAwardVOList();
            activityRepository.addAward(awardVOList);
            //添加策略配置
            StrategyVO strategyVO=activityConfigRich.getStrategyVO();
            activityRepository.addStrategy(strategyVO);
            //添加策略明细配置
            List<StrategyDetailVO> strategyDetailVOList=activityConfigRich.getStrategyVO().getStrategyDetailList();
            activityRepository.addStrategyDetailList(strategyDetailVOList);

            logger.info("创建活动配置完成，activityId:{}",req.getActivityId());


        }catch (DuplicateKeyException e){
            logger.error("创建活动配置失败，唯一索引冲突 activityId:{} reqJson:{}",req.getActivityId(), JSON.toJSONString(req),e);
            throw e;
        }



    }

    @Override
    public void updateActivity(ActivityConfigReq req) {
    //TODO:非核心功能后续补充
    }
}
