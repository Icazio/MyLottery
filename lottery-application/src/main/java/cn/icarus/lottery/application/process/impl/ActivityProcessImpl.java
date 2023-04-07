package cn.icarus.lottery.application.process.impl;

import cn.icarus.lottery.application.process.IActivityProcess;
import cn.icarus.lottery.application.process.req.DrawProcessReq;
import cn.icarus.lottery.application.process.res.DrawProcessResult;
import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.activity.model.req.PartakeReq;
import cn.icarus.lottery.domain.activity.model.res.PartakeResult;
import cn.icarus.lottery.domain.activity.model.vo.DrawOrderVO;
import cn.icarus.lottery.domain.activity.service.partake.IActivityPartake;
import cn.icarus.lottery.domain.strategy.model.req.DrawReq;
import cn.icarus.lottery.domain.strategy.model.res.DrawResult;
import cn.icarus.lottery.domain.strategy.model.vo.DrawAwardInfo;
import cn.icarus.lottery.domain.strategy.service.draw.IDrawExec;
import cn.icarus.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Icarus
 * @description
 * @date 2023/4/3 19:23
 */
@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {
      //1.领取活动【用活动ID和用户ID去获取策略ID和活动单号】
        PartakeResult partakeResult=activityPartake.doPartake(new PartakeReq(req.getuId(),req.getActivityId()));
        //如果没有领取/参加成功
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())){
            //返回没成功的原因
            return new DrawProcessResult(partakeResult.getCode(),partakeResult.getInfo());
        }
        Long strategyId=partakeResult.getStrategyId();
        Long takeId=partakeResult.getTakeId();



      // 2. 执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId, String.valueOf(takeId)));
        //如果抽奖失败
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            //返回没中奖
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardInfo drawAwardInfo = drawResult.getDrawAwardInfo();

        // 3. 结果落库
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, takeId, drawAwardInfo));

        // 4. 发送MQ，触发发奖流程

        // 5. 返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), drawAwardInfo);
    }

    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, Long takeId, DrawAwardInfo drawAwardInfo){
        //生成单号
        long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardInfo.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfo.getGrantType());
        drawOrderVO.setGrantDate(drawAwardInfo.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardInfo.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfo.getAwardType());
        drawOrderVO.setAwardName(drawAwardInfo.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfo.getAwardContent());
        return drawOrderVO;
    }

}
