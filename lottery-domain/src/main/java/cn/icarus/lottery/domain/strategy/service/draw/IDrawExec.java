package cn.icarus.lottery.domain.strategy.service.draw;

import cn.icarus.lottery.domain.strategy.model.req.DrawReq;
import cn.icarus.lottery.domain.strategy.model.res.DrawResult;

public interface IDrawExec {

    DrawResult doDrawExec(DrawReq req);
}
