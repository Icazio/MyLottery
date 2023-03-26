package cn.icarus.lottery.domain.strategy.service.draw;

import cn.icarus.lottery.infrastructure.po.StrategyDetail;

import java.util.List;

/**
 * @description
 * @date 2023/3/25 12:31
 */
public class DrawBase extends DrawConfig{

//    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList)
//    {
//        if(strategyMode!=1)return;// 策略方式「1:单项概率、2:总体概率」
//        IDrawAlgorithm drawAlgorithm=drawAlgorithmMap.get(strategyMode);
//
//        boolean existRateTuple= drawAlgorithm.isExistRateTuple(strategyId);
//        if(existRateTuple) return;
//
//        List<AwardRateInfo> awardRateInfoList=new ArrayList<>(strategyDetailList.size());
//        for(StrategyDetail strategyDetail:strategyDetailList)awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(),strategyDetail.getAwardRate()));
//
//        drawAlgorithm.initRateTuple(strategyId,awardRateInfoList);
//    }
    public void  checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList){
        return;
    }

    public void nnn(){
        return;
    }

}
