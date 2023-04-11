package cn.icarus.lottery.rpc;

import cn.icarus.lottery.rpc.req.DrawReq;
import cn.icarus.lottery.rpc.req.QuantificationDrawReq;
import cn.icarus.lottery.rpc.res.DrawRes;

/**
 * @author Icarus
 * @description 活动展台
                 * 1. 创建活动
                 * 2. 更新活动
                 * 3. 查询活动
 * @date 2023/4/9
 */
public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     * @param drawReq 请求参数【uid,activityId】
     * @return  抽奖结果【awardDTO、Result(code,info)】
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     * @param quantificationDrawReq 量化人群抽奖请求【用户ID、规则树ID、规则
     * @return 抽奖结果【awardDTO、Result(code,info)】
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);


}
