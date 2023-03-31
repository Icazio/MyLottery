package cn.icarus.lottery.domain.activity.service.deploy;

import cn.icarus.lottery.domain.activity.model.req.ActivityConfigReq;

/**
 * @author Icarus
 * @description
 * @date 2023/3/30
 */
public interface IActivityDeploy {

    /**
     * 创建活动信息
     *
     * @param req 活动配置信息
     */
    void createActivity(ActivityConfigReq req);


    /**
     * 修改活动信息
     *
     * @param req 活动配置信息
     */
    void updateActivity(ActivityConfigReq req);
}
