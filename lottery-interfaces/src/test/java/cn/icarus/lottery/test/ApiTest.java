package cn.icarus.lottery.test;

import cn.icarus.lottery.infrastructure.dao.IActivityDao;
import cn.icarus.lottery.infrastructure.po.Activity;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description：
 * @date: 2023/3/1919:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    private Logger logger= LoggerFactory.getLogger(ApiTest.class);

    @Resource
    IActivityDao activityDao;

    @Test
    public  void test_insert(){
        Activity activity=new Activity();
        activity.setActivityId(100002L);
        activity.setActivityName("测试活动名");
        activity.setActivityDesc("仅用于插入测试数据");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setState(0);
        activity.setCreator("ica");
        activityDao.insert(activity);
    }

    @Test
    public  void test_select(){
        Activity activity=activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }
}
