package Final.Project.dodo.model.mapper;

import Final.Project.dodo.base.BaseMapper;
import Final.Project.dodo.model.dto.OrderProductDto;
import Final.Project.dodo.model.entities.OrderProduct;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface OrderProductMapper extends BaseMapper<OrderProduct, OrderProductDto> {
}
