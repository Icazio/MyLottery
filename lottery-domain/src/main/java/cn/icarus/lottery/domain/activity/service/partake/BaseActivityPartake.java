package cn.icarus.lottery.domain.activity.service.partake;

import cn.icarus.lottery.common.Constants;
import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.activity.model.req.PartakeReq;
import cn.icarus.lottery.domain.activity.model.res.PartakeResult;
import cn.icarus.lottery.domain.activity.model.vo.ActivityBillVO;
import cn.icarus.lottery.domain.activity.model.vo.UserTakeActivityVO;
import cn.icarus.lottery.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Icarus
 * @description 活动领取模板抽象类
 * @date 2023/4/2 18:50
 */

public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    /**
     * 返回的可能情况：
     *  ①校验没通过，返回校验没通过的原因
     *  ②扣减活动库存失败，返回Result(Constants.ResponseCode.NO_UPDATE);
     *  ③领取活动信息【个人用户把活动信息写入到用户表】失败，返回失败原因【扣减失败/唯一索引冲突】
     *
     *  ④成功：返回的策略ID，用于继续完成抽奖步骤
     */
    public PartakeResult doPartake(PartakeReq req) {
        //1.查询是否存在未执行抽奖参与活动但【user-take-activity存在state==0，领取了但抽奖过成失败的，可以直接返回参与结果继续抽奖】
        UserTakeActivityVO noConsumedTakeActivityOrder=this.queryNoConsumedTakeActivityOrder(req.getActivityId(),req.getuId());
       if(noConsumedTakeActivityOrder!=null){
           return buildPartakeResult(noConsumedTakeActivityOrder.getStrategyId(),noConsumedTakeActivityOrder.getTakeId());
       }

        //2.查询活动账单
        //活动账单【库存、状态、日期、个人参与次数】
        //      * 用户ID uId; * 活动ID Long activityId;
        //      * 活动名称 String activityName;
        //      * 开始时间 Date beginDateTime;
        //      * 结束时间 Date endDateTime;
        //      * 库存剩余 Integer stockSurplusCount;
        //      * 活动状态：1编辑、2提审、3撤审、4通过、5运行(审核通过后worker扫描状态)、6拒绝、7关闭、8开启 Constants.ActivityState Integer state;
        //      * 策略ID Long strategyId;
        //      * 每人可参与次数 Integer takeCount;
        //      * 已参加次数 Integer userTakeLeftCount;
         ActivityBillVO activityBillVO=super.queryActivityBill(req);

        //3.活动信息校验处理【校验：①状态②日期③活动库存④个人参与次数】
        //进入ActivityPartakeImpl去进行checkActivityBill
        /*Result有以下可能：
        *   ①校验活动状态：活动状态不是DOING，即正在活动中------——返回错误码&信息：活动当前状态非可用
        *   ②校验活动日期：活动开始日期在用户参加活动生成之后或者活动结束时间在用户参与活动账单之前--------返回错误码&信息：活动时间范围非可用
        *   ③校验活动库存：活动库存小于等于0——返回错误码&信息：活动剩余库存非可用
        *   ④校验个人库存：个人活动剩余可领取次数小于等于0---返回错误码&信息：个人领取次数非可用
        *   ⑤校验全部通过---返回成功码&信息：成功
        * */
        Result checkResult =this.checkActivityBill(req,activityBillVO);
        //如果不是第5种情况，就把出错的情况返回回去
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())){
            return new PartakeResult(checkResult.getCode(),checkResult.getInfo());
        }

        //4.扣减活动库存【目前为直接对配置库中的lottery.activity直接操作表扣减库存，后续优化为Redis扣减】
        /*可能的返回情况
        *  ①修改失败：Result(Constants.ResponseCode.NO_UPDATE)
        *  ②成功
        * */
        Result subtractionActivityResult= this.subtractionActivityStock(req);
        //是第一个中情况的话，把第一种得到的情况返回上去。
        if(!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())){
            return new PartakeResult(subtractionActivityResult.getCode(),subtractionActivityResult.getInfo());
        }

        //5.领取活动信息【个人用户把活动信息写入到用户表】
        /*可能的返回情况
        *   ①扣减个人已参与次数失败：返回Result(Constants.ResponseCode.NO_UPDATE);
        *   ②参加活动唯一索引冲突：返回Result(Constants.ResponseCode.INDEX_DUP)；
        *   ③成功--得到Result
        * */
        //获取参与的单号:得到了雪花算法的生成器，用雪花算法生成器生成一个单号
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult=this.grabActivity(req,activityBillVO,takeId);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }

        // 封装结果【返回的策略ID，用于继续完成抽奖步骤】
        return buildPartakeResult(activityBillVO.getStrategyId(),takeId);
    }


    //以下全都是辅助上面的那个方法，放在实现里面去完成

    /**
     * 查询未参与的订单
     * @param activityId
     * @param uId
     * @return
     */
    protected abstract UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

    /**
     * 活动信息校验处理，把活动库存、状态、日期、个人参与次数
     * @param partake 参与活动请求
     * @param bill    活动账单
     * @return         校验结果
     */
    protected abstract Result checkActivityBill(PartakeReq partake, ActivityBillVO bill);

    /**
     * 扣减活动库存
     * @param req 参与活动请求
     * @return 扣减结果
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);

    /**
     * 将这个参与的这次记录【ActivityId(), getActivityName(),TakeCount(), UserTakeLeftCount, uId，PartakeDate, takeId】插入user_take_activity表中
     * @param partake 参与活动请求
     * @param bill     活动账单
     * @param takeId   活动领取ID
     * @return
     */
    protected abstract Result grabActivity(PartakeReq partake,ActivityBillVO bill,Long takeId);


    /**
     *
     * @param strategyId    策略ID
     * @param takeId        领取ID
     * @return              装起来
     */
    private  PartakeResult  buildPartakeResult(Long strategyId,Long takeId){
        PartakeResult partakeResult=new PartakeResult(Constants.ResponseCode.SUCCESS.getCode(),Constants.ResponseCode.SUCCESS.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }

}
