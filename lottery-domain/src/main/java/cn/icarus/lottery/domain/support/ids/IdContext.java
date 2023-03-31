package cn.icarus.lottery.domain.support.ids;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.support.ids.policy.RandomNumeric;
import cn.icarus.lottery.domain.support.ids.policy.ShortCode;
import cn.icarus.lottery.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Icarus
 * @description Id生成上下文
 *      通过配置注解@Configuration和Bean对象生成来吧策略生成ID服务包装到Map<>Constants.Ids.IIdGenerator对象中
 * @date 2023/3/31
 */
@Configuration
public class IdContext {

    /**
     * 创建ID生成策略对象
     * @param snowFlake 雪花算法，长码、大量                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             snowFlake
     * @param shortCode 日期算法、短码、少量、需要自己保证全局唯一性
     * @param numeric   随机算法、短码、大量、需要自己保证全局唯一性
     * @return   IIdGenerator 实现类
     */
    @Bean
    public Map<Constants.Ids,IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric numeric){
        Map<Constants.Ids,IIdGenerator> idGeneratorMap=new ConcurrentHashMap<>();
        idGeneratorMap.put(Constants.Ids.SnowFlake,snowFlake);
        idGeneratorMap.put(Constants.Ids.ShortCode,shortCode);
        idGeneratorMap.put(Constants.Ids.RandomNumeric,numeric);
        return idGeneratorMap;
    }
}
