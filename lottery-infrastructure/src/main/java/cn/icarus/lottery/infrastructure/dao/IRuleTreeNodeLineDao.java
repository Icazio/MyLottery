package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Icarus
 * @description
 * @date 2023/4/8
 */
@Mapper
public interface IRuleTreeNodeLineDao {

    /**
     * 查询某个节点的所有子节点
     * @param req   上一个节点的信息
     * @return  子节点列表
     */
    List<RuleTreeNodeLine> queryRuleTreeNodeLineList(RuleTreeNodeLine req);
}
