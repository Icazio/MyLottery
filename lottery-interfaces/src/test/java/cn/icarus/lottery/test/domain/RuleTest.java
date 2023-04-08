package cn.icarus.lottery.test.domain;

import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.rule.model.res.EngineResult;
import cn.icarus.lottery.domain.rule.service.engine.EngineFilter;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Icarus
 * @description
 * @date 2023/4/8 16:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTest {

    private Logger logger= LoggerFactory.getLogger(RuleTest.class);

    @Resource
    private EngineFilter engineFilter;

    @Test
    public void test_process(){
        DecisionMatterReq matterReq=new DecisionMatterReq();
        matterReq.setTreeId(2110081902L);
        matterReq.setUserId("Daisy");
        matterReq.setValMap(new HashMap<String,Object>(){{
            put("gender","man");
            put("age","25");
        }});

        EngineResult res= engineFilter.process(matterReq);

        logger.info("请求参数：{}",JSON.toJSONString(matterReq));
        logger.info("测试结果：{}", JSON.toJSONString(res));


    }
}
