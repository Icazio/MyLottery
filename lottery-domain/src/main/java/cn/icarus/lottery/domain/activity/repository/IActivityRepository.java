package cn.icarus.lottery.domain.activity.repository;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.activity.model.vo.ActivityVO;
import cn.icarus.lottery.domain.activity.model.vo.AwardVO;
import cn.icarus.lottery.domain.activity.model.vo.StrategyDetailVO;
import cn.icarus.lottery.domain.activity.model.vo.StrategyVO;

import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30 14:44
 */


public interface IActivityRepository {

    /**
     * 添加活动配置
     * @param activityVO 活动配置
     */
    void addActivity(ActivityVO activityVO);

    /**
     * 添加奖品配置集合
     * @param awardVOList 奖品配置集合
     */
    void addAward(List<AwardVO> awardVOList);

    /**
     * 添加策略配置
     * @param strategyVO 策略配置
     */
    void addStrategy(StrategyVO strategyVO);

    /**
     * 添加策略明细配置
     * @param strategyDetailVOList  策略明细配置
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailVOList);

    /**
     * 变更活动状态
     * @param activityId    活动ID
     * @param beforeState   修改前状态
     * @param afterState    修改后状态
     * @return              更新结果
     */
    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState,Enum<Constants.ActivityState> afterState);

}
