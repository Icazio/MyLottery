package cn.icarus.lottery.domain.support.ids.policy;

import cn.icarus.lottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Icarus
 * @description     工具类生成 org.apache.commons.lang3.RandomStringUtils
 * @date 2023/3/31 13:24
 */
@Component
public class RandomNumeric implements IIdGenerator {

    /**
     * 11是位数
     * @return 11位id
     */
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
