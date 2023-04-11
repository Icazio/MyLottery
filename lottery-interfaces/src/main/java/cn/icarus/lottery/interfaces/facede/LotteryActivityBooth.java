package cn.icarus.lottery.interfaces.facede;

import cn.icarus.lottery.application.process.IActivityProcess;
import cn.icarus.lottery.application.process.req.DrawProcessReq;
import cn.icarus.lottery.application.process.res.DrawProcessResult;
import cn.icarus.lottery.application.process.res.RuleQuantificationCrowdResult;
import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.strategy.model.vo.DrawAwardVO;
import cn.icarus.lottery.interfaces.assembler.IMapping;
import cn.icarus.lottery.rpc.ILotteryActivityBooth;
import cn.icarus.lottery.rpc.dto.AwardDTO;
import cn.icarus.lottery.rpc.req.DrawReq;
import cn.icarus.lottery.rpc.req.QuantificationDrawReq;
import cn.icarus.lottery.rpc.res.DrawRes;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author Icarus
 * @description
 * @date 2023/3/20 15:41
 */
@Controller // * 该注解表示该类是一个服务类，可以被Spring容器自动扫描并注入到其他需要使用该类的地方。
public class LotteryActivityBooth implements ILotteryActivityBooth {

    private Logger logger= LoggerFactory.getLogger(LotteryActivityBooth.class);

    @Resource
    private IActivityProcess activityProcess;

    @Resource
    private IMapping<DrawAwardVO, AwardDTO> awardMapping;


    @Override
    public DrawRes doDraw(DrawReq drawReq) {
        try{
            logger.info("抽奖.开始 uId:{} activityId:{}",drawReq.getuId(),drawReq.getActivityId());

            //1.执行抽奖
            DrawProcessResult drawProcessResult=activityProcess.doDrawProcess(new DrawProcessReq(drawReq.getuId(),drawReq.getActivityId()));
            if(!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())){
                logger.error("抽奖.失败(抽奖过程异常）uId:{} activityId{}",drawReq.getuId(),drawReq.getActivityId());
                return new DrawRes(drawProcessResult.getCode(),drawProcessResult.getInfo());
            }

            //2.数据转换
            DrawAwardVO drawAwardVO=drawProcessResult.getDrawAwardVO();
            AwardDTO awardDTO=awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(drawReq.getActivityId());

            //3.封装数据
            DrawRes drawRes=new DrawRes(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo() );
            drawRes.setAwardDTO(awardDTO);

            logger.info("抽奖.完成 uId:{} activityId:{}",drawReq.getuId(),drawReq.getActivityId());

            return drawRes;
        }catch(Exception e){
            logger.error("抽奖.失败(接口层doDraw过程失败) uId:{} activityId:{}",drawReq.getuId(),drawReq.getActivityId());
            return new DrawRes(Constants.ResponseCode.UNKNOWN_ERROR.getCode(), Constants.ResponseCode.UNKNOWN_ERROR.getInfo());
        }
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq) {
        try{
            logger.info("量化人群抽奖，开始 uId:{} treeId:{}",quantificationDrawReq.getuId(),quantificationDrawReq.getTreeId());

            //1.执行规则引擎，获取用户可以参与的活动号
            RuleQuantificationCrowdResult ruleQuantificationCrowResult=activityProcess.doRuleQuantificationCrowd(new DecisionMatterReq(quantificationDrawReq.getTreeId(),quantificationDrawReq.getuId(),quantificationDrawReq.getValMap()));
            if(!Constants.ResponseCode.SUCCESS.getCode().equals(ruleQuantificationCrowResult.getCode())){
                logger.error("量化人群抽奖，失败(规则引擎执行异常) uId：{} treeId：{} activityId:{}", quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId(),ruleQuantificationCrowResult.getActivityId());
                return new DrawRes(ruleQuantificationCrowResult.getCode(), ruleQuantificationCrowResult.getInfo());
            }

            //2.执行抽奖
            DrawProcessResult drawProcessResult=activityProcess.doDrawProcess(new DrawProcessReq(quantificationDrawReq.getuId(),ruleQuantificationCrowResult.getActivityId()));
            if(!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())){
                logger.error("量化人群抽奖，失败(抽奖过程异常规) uId：{} treeId：{} activityId:{}", quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId(),ruleQuantificationCrowResult.getActivityId());
                return new DrawRes(drawProcessResult.getCode(),drawProcessResult.getInfo());
            }

            //3.数据转换
            DrawAwardVO drawAwardVO=drawProcessResult.getDrawAwardVO();
            AwardDTO awardDTO=awardMapping.sourceToTarget(drawAwardVO);
            awardDTO.setActivityId(ruleQuantificationCrowResult.getActivityId());

            //4.封装数据
            DrawRes drawRes=new DrawRes(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo() );
            drawRes.setAwardDTO(awardDTO);

            logger.info("量化人群完成 uId：{} treeId：{} activityId:{}", quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId(),ruleQuantificationCrowResult.getActivityId());

            return drawRes;
        }catch(Exception e){
            logger.error("量化人群抽奖，失败 reqJson：{}",JSON.toJSONString(quantificationDrawReq), e);
            return new DrawRes(Constants.ResponseCode.UNKNOWN_ERROR.getCode(), Constants.ResponseCode.UNKNOWN_ERROR.getInfo());
        }
    }
}
