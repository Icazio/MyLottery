package cn.icarus.lottery.domain.award.service.goods.impl;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.award.model.req.GoodsReq;
import cn.icarus.lottery.domain.award.model.res.DistributionRes;
import cn.icarus.lottery.domain.award.service.goods.DistributionBase;
import cn.icarus.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @author Icarus
 * @description 优惠券商品
 * @date 2023/3/29 21:26
 */
@Component
public class CouponGoods  extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        //模拟调用优惠券发放接口
        logger.info("模拟调用优惠券发放接口 uId:{} awardContent:{}",req.getuId(),req.getAwardContent());
        //更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.CouponGoods.getCode();
    }
}
