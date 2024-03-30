package Final.Project.dodo.model.mapper;

import Final.Project.dodo.base.BaseMapper;
import Final.Project.dodo.model.dto.AddressDto;
import Final.Project.dodo.model.entities.Address;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressDto> {
}
