package cn.icarus.lottery.test;

import cn.icarus.lottery.domain.strategy.model.vo.AwardRateInfo;
import cn.icarus.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @date 2023/3/26 21:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DrawAlgorithmTest {

    //@Resource(name="singleRateRandomDrawAlgorithm")
    @Resource(name="entiretyRateRandomDrawAlgorithm")
    private IDrawAlgorithm randomDrawAlgorithm;

    @Before
    public void init(){
        BigDecimal i=new BigDecimal(0.05);
        //奖品信息
        List<AwardRateInfo> strategyList=new ArrayList<>();
        strategyList.add(new AwardRateInfo("一等奖：Imac",new BigDecimal(0.05)));
        strategyList.add(new AwardRateInfo("二等奖：ipad",new BigDecimal(0.15)));
        strategyList.add(new AwardRateInfo("三等奖：iphone",new BigDecimal(0.20)));
        strategyList.add(new AwardRateInfo("四等奖：AirPods",new BigDecimal(0.25)));
        strategyList.add(new AwardRateInfo("五等奖：充电宝",new BigDecimal(0.35)));

        //初始数据
        randomDrawAlgorithm.initRateTuple(100001L,strategyList);
    }

    @Test
    public void test_randomDrawAlgorithm(){
        List<String> excludeAwardId=new ArrayList<>();
        excludeAwardId.add("二等奖：ipad");
        excludeAwardId.add("四等奖：AirPods");

        for(int i=0;i<20;i++){
            System.out.println("中奖结果："+randomDrawAlgorithm.randomDraw(100001L,excludeAwardId));
        }
    }
}
//https://blog.csdn.net/xb12369/article/details/88074135
