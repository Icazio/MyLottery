package cn.icarus.lottery.rpc.res;

import cn.icarus.lottery.common.Result;
import cn.icarus.lottery.rpc.dto.AwardDTO;

import java.io.Serializable;

/**
 * @author Icarus
 * @description 抽奖结果【awardDTO、DrawRes[code、info]】
 * @date 2023/4/9 10:29
 */
public class DrawRes extends Result implements Serializable {
    private AwardDTO awardDTO;

    public DrawRes(String code, String info) {
        super(code, info);
    }


    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }
}
