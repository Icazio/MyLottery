package cn.icarus.lottery.domain.activity.service.partake;

import cn.icarus.lottery.domain.activity.model.req.PartakeReq;
import cn.icarus.lottery.domain.activity.model.res.PartakeResult;

/**
 * @author Icarus
 * @description c抽奖活动参与接口
 * @date 2023/3/30
 */

public interface IActivityPartake {

    /**
     * 参与活动
     * @param req 参与请求
     *     用户ID uId
     *     活动ID activityId
     *     活动参与时间 partakeDate
     * @return   领取结果
     *     策略Id strategyId
     */
    PartakeResult doPartake(PartakeReq req);
}
