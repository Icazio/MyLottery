package cn.icarus.lottery.domain.award.service.factory;

import cn.icarus.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @author Icarus
 * @description
 * @date 2023/3/29 19:41
 */
@Service
public class DistributionGoodsFactory extends  GoodsConfig{
    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }

}
