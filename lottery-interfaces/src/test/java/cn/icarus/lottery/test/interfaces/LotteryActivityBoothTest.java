package cn.icarus.lottery.test.interfaces;

import cn.icarus.lottery.rpc.ILotteryActivityBooth;
import cn.icarus.lottery.rpc.req.DrawReq;
import cn.icarus.lottery.rpc.req.QuantificationDrawReq;
import cn.icarus.lottery.rpc.res.DrawRes;
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
 * @date 2023/4/9 19:14
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryActivityBoothTest {
    private Logger logger= LoggerFactory.getLogger(LotteryActivityBoothTest.class);

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void test_doDraw(){
        DrawReq drawReq=new DrawReq("Daisy",100001L);
        DrawRes drawRes=lotteryActivityBooth.doDraw(drawReq);
        logger.info("请求参数：{}", JSON.toJSONString(drawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));
    }

    @Test
    public void test_doQualificationDraw(){
        QuantificationDrawReq quantificationDrawReq=new QuantificationDrawReq();
        quantificationDrawReq.setuId("Daisy");
        quantificationDrawReq.setTreeId(2110081902L);

        quantificationDrawReq.setValMap(new HashMap<String,Object>(){{
            put("gender","man");
            put("age","24");
        }});
        DrawRes drawRes=lotteryActivityBooth.doQuantificationDraw(quantificationDrawReq);
        logger.info("请求参数：{}", JSON.toJSONString(quantificationDrawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));

    }
}
