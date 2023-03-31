package cn.icarus.lottery.domain.activity.model.aggregate;

import cn.icarus.lottery.domain.activity.model.vo.ActivityVO;
import cn.icarus.lottery.domain.activity.model.vo.AwardVO;
import cn.icarus.lottery.domain.activity.model.vo.StrategyVO;

import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30 16:34
 */
public class ActivityConfigRich {
    /**
     * 活动配置
     */
    private ActivityVO activityVO;

    /**
     * 策略配置
     */
    private StrategyVO strategyVO;

    /**
     * 奖品配置
     */
    private List<AwardVO> awardVOList;

    public ActivityConfigRich() {
    }

    public ActivityConfigRich(ActivityVO activityVO, StrategyVO strategyVO, List<AwardVO> awardVOList) {
        this.activityVO = activityVO;
        this.strategyVO = strategyVO;
        this.awardVOList = awardVOList;
    }

    public ActivityVO getActivityVO() {
        return activityVO;
    }

    public void setActivityVO(ActivityVO activityVO) {
        this.activityVO = activityVO;
    }

    public StrategyVO getStrategyVO() {
        return strategyVO;
    }

    public void setStrategyVO(StrategyVO strategyVO) {
        this.strategyVO = strategyVO;
    }

    public List<AwardVO> getAwardVOList() {
        return awardVOList;
    }

    public void setAwardVOList(List<AwardVO> awardVOList) {
        this.awardVOList = awardVOList;
    }
}


