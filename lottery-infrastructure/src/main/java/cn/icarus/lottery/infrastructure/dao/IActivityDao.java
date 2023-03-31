package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.domain.activity.model.vo.AlterStateVO;
import cn.icarus.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description 数据访问对象
 * @date 2023/3/19 20:36
 */

@Mapper
public interface IActivityDao {
    void insert(Activity req); //新建数据
    Activity queryActivityById(Long activityId);  //提供查询操作

    /**
     * 变更活动状态
     *
     * @param alterStateVO  [activityId、beforeState、afterState]
     * @return 更新数量
     */

    int alterState(AlterStateVO alterStateVO);
}
