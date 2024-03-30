package Final.Project.dodo.model.mapper;

import Final.Project.dodo.base.BaseMapper;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.dto.UserDto;
import Final.Project.dodo.model.entities.ProductSize;
import Final.Project.dodo.model.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}
