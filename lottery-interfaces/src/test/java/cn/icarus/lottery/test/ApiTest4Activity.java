package cn.icarus.lottery.test;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.award.model.req.GoodsReq;
import cn.icarus.lottery.domain.award.model.res.DistributionRes;
import cn.icarus.lottery.domain.award.service.factory.DistributionGoodsFactory;
import cn.icarus.lottery.domain.award.service.goods.IDistributionGoods;
import cn.icarus.lottery.domain.strategy.model.req.DrawReq;
import cn.icarus.lottery.domain.strategy.model.res.DrawResult;
import cn.icarus.lottery.domain.strategy.model.vo.DrawAwardInfo;
import cn.icarus.lottery.domain.strategy.service.draw.IDrawExec;
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
public class ApiTest4Activity {
    private Logger logger= LoggerFactory.getLogger(ApiTest4Activity.class);

    @Resource
    IActivityDao activityDao;
    @Resource
    IDrawExec iDrawExec;
    @Resource
    DistributionGoodsFactory distributionGoodsFactory;

    @Test
    public void test_doDraw(){
        iDrawExec.doDrawExec(new DrawReq("张三",10001L));
        iDrawExec.doDrawExec(new DrawReq("李四",10001L));
        iDrawExec.doDrawExec(new DrawReq("王五",10001L));
        iDrawExec.doDrawExec(new DrawReq("赵六",10001L));
        iDrawExec.doDrawExec(new DrawReq("陈七",10001L));

    }
    @Test
    public void test_award() {
        // 执行抽奖
        DrawResult drawResult = iDrawExec.doDrawExec(new DrawReq("小傅哥", 10001L));

        // 判断抽奖结果
        Integer drawState = drawResult.getDrawState();
        if (Constants.DrawState.FAIL.getCode().equals(drawState)) {
            logger.info("未中奖 DrawAwardInfo is null");
            return;
        }

        // 封装发奖参数，orderId：2109313442431 为模拟ID，需要在用户参与领奖活动时生成
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();
        GoodsReq goodsReq = new GoodsReq(drawResult.getuId(), "2109313442431", drawAwardInfo.getAwardId(), drawAwardInfo.getAwardName(), drawAwardInfo.getAwardContent());

        // 根据 awardType 从抽奖工厂中获取对应的发奖服务
        IDistributionGoods distributionGoodsService = distributionGoodsFactory.getDistributionGoodsService(drawAwardInfo.getAwardType());
        DistributionRes distributionRes = distributionGoodsService.doDistribution(goodsReq);

        logger.info("测试结果：{}", JSON.toJSONString(distributionRes));
    }

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
