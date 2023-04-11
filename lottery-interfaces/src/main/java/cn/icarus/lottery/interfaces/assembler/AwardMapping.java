package cn.icarus.lottery.interfaces.assembler;

import cn.icarus.lottery.domain.strategy.model.vo.DrawAwardVO;
import cn.icarus.lottery.rpc.dto.AwardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Icarus
 * @description
 * @date 2023/4/9 9:28
 */
@Mapper(componentModel="spring",unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVO, AwardDTO>{
    @Mapping(target = "userId",source ="uId")

    @Override
    AwardDTO sourceToTarget(DrawAwardVO var);

    @Override
    DrawAwardVO targetToSource(AwardDTO var);
}
