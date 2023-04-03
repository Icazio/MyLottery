package cn.icarus.lottery.domain.activity.service.partake;

import cn.icarus.lottery.domain.activity.model.req.PartakeReq;
import cn.icarus.lottery.domain.activity.model.vo.ActivityBillVO;
import cn.icarus.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;

/**
 * @author Icarus
 * @description
 * @date 2023/4/2 18:51
 */
public class ActivityPartakeSupport {
    @Resource
    protected IActivityRepository activityRepository;

    /**
     * 查询活动账单信息
     * @param req 参与活动请求
     *             用户ID uId
     *             活动ID activityId
     *             活动参与时间 partakeDate
     * @return  活动账单【库存、状态、日期、个人参与次数】
     *          * 用户ID uId;
     *          * 活动ID Long activityId;
     *          * 活动名称 String activityName;
     *          * 开始时间 Date beginDateTime;
     *          * 结束时间 Date endDateTime;
     *          * 库存剩余 Integer stockSurplusCount;
     *          * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启 Constants.ActivityState Integer state;
     *          * 策略ID Long strategyId;
     *          * 每人可参与次数 Integer takeCount;
     *          * 已参加次数 Integer userTakeLeftCount;
     */
    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }
}
