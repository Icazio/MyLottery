package cn.icarus.lottery.domain.strategy.service.algorithm.impl;

import cn.icarus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.icarus.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @date 2023/3/25 17:32
 */
@Component("defaultRateRandomDrawAlgorithm")
public class DefaultRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        BigDecimal differenceDenominator=BigDecimal.ZERO;

        //排除掉不在抽奖范围的奖品ID集合  //比如说被抽掉了
        List<AwardRateInfo> differenceAwardRateList=new ArrayList<>();
        List<AwardRateInfo> awardRateIntervalValList=awardRateInfoMap.get(strategyId);//先获取所有的奖品和它的概率

        for(AwardRateInfo awardRateInfo:awardRateIntervalValList){
            String awardId=awardRateInfo.getAwardId(); //获取所有奖品的id
            if(excludeAwardIds.contains(awardId))continue; //如果在被排除范围内
            differenceAwardRateList.add(awardRateInfo); //把剩下的加入到differenceAward列表中
            differenceDenominator=differenceDenominator.add(awardRateInfo.getAwardRate());//把剩余的所有概率加起来
        }

        //前置判断
        if(differenceAwardRateList.size()==0)return "";//如果一个也没有剩下，说明全部被抽完了,返回空串表示没奖
        if(differenceAwardRateList.size()==1)return differenceAwardRateList.get(0).getAwardId();//如果就剩一个了，直接返回这个奖品作为中奖结果

        //获取随机概率值
        SecureRandom secureRandom=new SecureRandom();
        int randomVal=secureRandom.nextInt(100)+1;

        String awardId="";
        int cursor=0;
        for(AwardRateInfo awardRateInfo:differenceAwardRateList){
            int rateVal=awardRateInfo.getAwardRate().divide(differenceDenominator,BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();//比如说原来的概率是30%，但是一等奖被抽完了，所以剩下所有奖品是80%，调整后概率变为3/8==0.375
            if(randomVal<=(cursor+rateVal)){//在这段范围内--中奖
                awardId=awardRateInfo.getAwardId();
                break;
            }
            cursor+=rateVal;
        }
        return awardId;//返回中奖结果
    }
}
