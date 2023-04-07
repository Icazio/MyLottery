package cn.icarus.lottery.domain.strategy.service.draw;

import cn.icarus.lottery.domain.strategy.model.req.DrawReq;
import cn.icarus.lottery.domain.strategy.model.res.DrawResult;

/**
 * @author Icarus
 * @description ：抽奖执行接口
 * @date 2023/3/26 17:26
 */

public interface IDrawExec {

    /**
     * 抽奖方法
     * <接口方法必须使用javadoc注释>
     * @param req 抽奖参数：用户ID,策略ID(用户所参加的那个抽奖活动
     * @return 中奖结果：用户ID,策略ID,中奖状态，奖品信息（奖品ID，奖品名字）
     */
    DrawResult doDrawExec(DrawReq req);
}
