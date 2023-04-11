package cn.icarus.lottery.application.process.res;

import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * @author Icarus
 * @description 【DrawAwardVO：
 * 中奖奖品信息【奖品ID、奖品类型、奖品名称、奖品内容】】
 * @date 2023/4/3 19:15
 */
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardVO;

    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardVO) {
        super(code, info);
        this.drawAwardVO = drawAwardVO;
    }

    public DrawAwardVO getDrawAwardVO() {
        return drawAwardVO;
    }

    public void setDrawAwardVO(DrawAwardVO drawAwardVO) {
        this.drawAwardVO = drawAwardVO;
    }

}
