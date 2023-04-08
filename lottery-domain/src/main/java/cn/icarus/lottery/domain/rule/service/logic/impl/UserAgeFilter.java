package cn.icarus.lottery.domain.rule.service.logic.impl;

import cn.icarus.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.icarus.lottery.domain.rule.service.logic.BasicLogic;
import org.springframework.stereotype.Component;

/**
 * @author Icarus
 * @description 年龄规则
 * @date 2023/4/7 22:41
 */

@Component
public class UserAgeFilter extends BasicLogic {
    @Override
    public String matterValue(DecisionMatterReq decisionMatterReq) {
        return decisionMatterReq.getValMap().get("age").toString();
    }
}
