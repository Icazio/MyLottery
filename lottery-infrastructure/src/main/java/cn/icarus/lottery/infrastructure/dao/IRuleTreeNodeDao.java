package cn.icarus.lottery.infrastructure.dao;

import cn.icarus.lottery.infrastructure.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author Icarus
 * @description
 * @date 2023/4/8
 */
@Mapper
public interface IRuleTreeNodeDao {


    /**
     * 查询规则树节点信息
     * @param treeId    规则树ID
     * @return  规则树节点信息
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);
}
