package cn.icarus.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.icarus.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Icarus
 * @description 使用hutool工具类生成的雪花算法--策略ID实现
 * @date 2023/3/31 13:26
 */
@Component
public class SnowFlake implements IIdGenerator {

    /**注意这个类型不是本类哈*/
    private Snowflake snowflake;

    @PostConstruct
    public  void init(){
        //0~31位，可以采用配置的方式使用
        long workerId;
        try{
            workerId= NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch(Exception e){
            workerId=NetUtil.getLocalhostStr().hashCode();
        }
        workerId=workerId>>16&31;

        long dataCenterId=1L;
        snowflake= IdUtil.createSnowflake(workerId,dataCenterId);
    }

    @Override
    public synchronized long nextId() {
        return snowflake.nextId();
    }
}
