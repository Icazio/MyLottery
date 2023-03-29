package cn.icarus.lottery.domain.strategy.service.algorithm.impl;

import cn.icarus.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * @author Icarus
 * @description 单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 * @date 2023/3/25 18:05
 */
@Component("singleRateRandomDrawAlgorithm")
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {
    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
       //获取策略对应的元组
        String[] rateTuple=super.rateTupleMap.get(strategyId);
        assert rateTuple!=null;
        //assert condition，当 condition 为 true，就继续往下运行；当 condition 为 false，就抛出一个错误，程序停止。

       //随机索引
        int randomVal=new SecureRandom().nextInt(100)+1;
        int idx=super.hashIdx(randomVal);

        //返回结果
        String awardId=rateTuple[idx];

        //如果正好抽到已经没了的奖，就返回没中奖。
        if(excludeAwardIds.contains(awardId)) {
            return"未中奖";
        }

        return awardId;
    }
}
