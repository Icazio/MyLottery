package cn.icarus.lottery.domain.rule.service.engine;

import cn.icarus.lottery.domain.rule.service.logic.ILogic;
import cn.icarus.lottery.domain.rule.service.logic.impl.UserAgeFilter;
import cn.icarus.lottery.domain.rule.service.logic.impl.UserGenderFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Icarus
 * @description 规则配置
 * @date 2023/4/8 9:41
 */
public class EngineConfig {
    protected static Map<String, ILogic> logicMap=new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;

    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init(){
        logicMap.put("userAge",userAgeFilter);
        logicMap.put("userGender",userGenderFilter);
    }
}
