package cn.icarus.lottery.domain.strategy.service.draw;

import cn.icarus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 * @date 2023/3/25 12:31
 */

public class DrawConfig {
    @Resource
    private IDrawAlgorithm defaultRateRandomDrawAlgorithm;

    @Resource
    private IDrawAlgorithm singleRateRandomDrawAlgorithm;

    protected static Map<Integer,IDrawAlgorithm> drawAlgorithmMap=new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        drawAlgorithmMap.put(1,defaultRateRandomDrawAlgorithm);
        drawAlgorithmMap.put(2,singleRateRandomDrawAlgorithm);
    }
}
