package cn.icarus.lottery.domain.award.service.factory;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.award.service.goods.IDistributionGoods;
import cn.icarus.lottery.domain.award.service.goods.impl.CouponGoods;
import cn.icarus.lottery.domain.award.service.goods.impl.DescGoods;
import cn.icarus.lottery.domain.award.service.goods.impl.PhysicalGoods;
import cn.icarus.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Icarus
 * @description
 * @date 2023/3/29 19:42
 */
public class GoodsConfig {
    /**奖品发放策略组*/
    protected  static Map<Integer, IDistributionGoods> goodsMap=new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init() {
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}
