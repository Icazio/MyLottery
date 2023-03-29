package cn.icarus.lottery.domain.strategy.service.draw;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Icarus
 * @description 抽奖统一配置信息类
 * @date 2023/3/25 12:31
 */

public class DrawConfig {
    @Resource
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    /**抽奖策略组*/
    protected static Map<Integer,IDrawAlgorithm> drawAlgorithmGroup=new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        drawAlgorithmGroup.put(Constants.StrategyMode.ENTIRETY.getCode(),entiretyRateRandomDrawAlgorithm);
        drawAlgorithmGroup.put(Constants.StrategyMode.SINGLE.getCode(), singleRateRandomDrawAlgorithm);
    }
}
