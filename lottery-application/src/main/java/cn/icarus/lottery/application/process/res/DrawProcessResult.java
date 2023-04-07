package cn.icarus.lottery.application.process.res;

import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.strategy.model.vo.DrawAwardInfo;

/**
 * @author Icarus
 * @description 【DrawAwardInfo：
 * 中奖奖品信息【奖品ID、奖品类型、奖品名称、奖品内容】】
 * @date 2023/4/3 19:15
 */
public class DrawProcessResult extends Result {

    private DrawAwardInfo drawAwardInfo;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }

}
