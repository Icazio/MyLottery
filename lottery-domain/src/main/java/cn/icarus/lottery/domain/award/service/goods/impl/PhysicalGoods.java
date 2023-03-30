package cn.icarus.lottery.domain.award.service.goods.impl;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.award.model.req.GoodsReq;
import cn.icarus.lottery.domain.award.model.res.DistributionRes;
import cn.icarus.lottery.domain.award.service.goods.DistributionBase;
import cn.icarus.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @author Icarus
 * @description 实物类奖品
 * @date 2023/3/29 19:40
 */
@Component

public class PhysicalGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        // 模拟调用实物发奖
        logger.info("模拟调用实物发奖 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());

        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(),Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.PhysicalGoods.getCode();
    }
}
