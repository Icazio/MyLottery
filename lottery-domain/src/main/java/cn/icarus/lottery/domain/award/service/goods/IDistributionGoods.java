package cn.icarus.lottery.domain.award.service.goods;

import cn.icarus.lottery.domain.award.model.req.GoodsReq;
import cn.icarus.lottery.domain.award.model.res.DistributionRes;

/**
 * @author Icarus
 * @description
 * @date 2023/3/29
 */
public interface IDistributionGoods {
    /**
     * 奖品配送接口。
     * @param req 物品信息
     * @return    分发结果
     */
    DistributionRes doDistribution(GoodsReq req);


    /**
     * 获取当前奖品的类型
     * @return 奖品类型（0.DESC，1.RedeemCodeGoods，2.CouponGoods，3.PhysicalGoods）
     */
    Integer getDistributionGoodsName();

}
