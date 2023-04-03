package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.domain.activity.model.vo.AlterStateVO;
import cn.icarus.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Icarus
 * @description 数据访问对象
 * @date 2023/3/19 20:36
 */

@Mapper
public interface IActivityDao {
    void insert(Activity req); //新建数据

    /**
     * 提供查询操作
     * @param activityId  活动ID
     * @return  活动【】
     */
    Activity queryActivityById(Long activityId);

    /**
     * 变更活动状态
     *
     * @param alterStateVO  [activityId、beforeState、afterState]
     * @return 更新数量
     */

    int alterState(AlterStateVO alterStateVO);

    /**
     * 扣减活动库存
     * @param activityId 活动id
     * @return  扣减结果--更新后的stockSurplusCount
     */
    int subtractionActivityStock(Long activityId);
}
