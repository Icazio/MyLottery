package cn.icarus.lottery.application.process;

import cn.icarus.lottery.application.process.req.DrawProcessReq;
import cn.icarus.lottery.application.process.res.DrawProcessResult;
import cn.icarus.lottery.application.process.res.RuleQuantificationCrowdResult;
import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;

/**
 * @author Icarus
 * @description  活动抽奖流程编排接口
 * @date 2023/4/3
 */
public interface IActivityProcess {
    /**
     * 执行抽奖流程
     * @param req 抽奖请求【用户ID、活动ID】
     * @return    抽奖结果【DrawAwardInfo： 中奖奖品信息【奖品ID、奖品类型、奖品名称、奖品内容】】
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);

    /**
     * 规则量化人群，返回可参与的活动ID
     * @param req   规则请求
     * @return      量化结果，用户可以参与的活动ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);

}
