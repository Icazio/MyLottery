package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.domain.strategy.model.vo.AwardBriefVO;
import cn.icarus.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAwardDao {
    AwardBriefVO queryAwardInfo(String id);

    /**
     * 插入奖品配置
     *
     * @param list 奖品配置
     */
    void insertList(List<Award> list);
}
