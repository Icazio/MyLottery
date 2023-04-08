package cn.icarus.lottery.infrastructure.dao;


import cn.icarus.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRuleTreeDao {

    RuleTree queryRuleTree(Long treeId);
}
