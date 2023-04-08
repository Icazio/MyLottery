package cn.icarus.lottery.domain.rule.service.logic;


import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @author Icarus
 * @description 规则过滤器接口
 * @date 2023/4/7
 */
public interface ILogic {

    /**
     * 逻辑决策器
     * @param matterValue           决策值
     * @param treeNodeLineInfoList  决策节点【  】
     * @return                      下一个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);

    /**
     * 获取决策值
     * @param decisionMatterReq 决策物料
     * @return                  决策值
     */
    String matterValue(DecisionMatterReq decisionMatterReq);
}
