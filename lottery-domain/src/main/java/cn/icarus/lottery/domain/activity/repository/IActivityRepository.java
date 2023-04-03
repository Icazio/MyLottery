package cn.icarus.lottery.domain.activity.repository;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.domain.activity.model.req.PartakeReq;
import cn.icarus.lottery.domain.activity.model.vo.*;

import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30 14:44
 */


public interface IActivityRepository {

    /**
     * 添加活动配置
     * @param activityVO 活动配置
     */
    void addActivity(ActivityVO activityVO);

    /**
     * 添加奖品配置集合
     * @param awardVOList 奖品配置集合
     */
    void addAward(List<AwardVO> awardVOList);

    /**
     * 添加策略配置
     * @param strategyVO 策略配置
     */
    void addStrategy(StrategyVO strategyVO);

    /**
     * 添加策略明细配置
     * @param strategyDetailVOList  策略明细配置
     */
    void addStrategyDetailList(List<StrategyDetailVO> strategyDetailVOList);

    /**
     * 变更活动状态
     * @param activityId    活动ID
     * @param beforeState   修改前状态
     * @param afterState    修改后状态
     * @return              更新结果
     */
    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> beforeState,Enum<Constants.ActivityState> afterState);

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
    ActivityBillVO queryActivityBill(PartakeReq req);

    /**
     * 扣减活动库存
     * @param activityId   活动ID
     * @return      扣减结果
     */
    int subtractionActivityStock(Long activityId);

}
