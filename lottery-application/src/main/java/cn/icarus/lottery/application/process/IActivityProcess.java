package cn.icarus.lottery.application.process;

import cn.icarus.lottery.application.process.req.DrawProcessReq;
import cn.icarus.lottery.application.process.res.DrawProcessResult;

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

}
