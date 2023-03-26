package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAwardDao {
    Award queryAwardInfo(String id);
}
