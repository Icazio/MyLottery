package cn.icarus.lottery.test.application;

import cn.icarus.lottery.application.process.IActivityProcess;
import cn.icarus.lottery.application.process.req.DrawProcessReq;
import cn.icarus.lottery.application.process.res.DrawProcessResult;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Icarus
 * @description
 * @date 2023/4/5 13:15
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityProcessTest {

    private Logger logger= LoggerFactory.getLogger(ActivityProcessTest.class);

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess(){
        DrawProcessReq req=new DrawProcessReq();
        req.setActivityId(100001L);
        req.setuId("Daisy");
        DrawProcessResult drawProcessResult=activityProcess.doDrawProcess(req);


        logger.info("请求入参：{}",JSON.toJSONString(req));
        logger.info("测试结果：{}",JSON.toJSONString(drawProcessResult));
    }
}
