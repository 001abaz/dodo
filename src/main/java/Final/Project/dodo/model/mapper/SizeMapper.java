package Final.Project.dodo.model.mapper;

import Final.Project.dodo.base.BaseMapper;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.dto.SizeDto;
import Final.Project.dodo.model.entities.ProductSize;
import Final.Project.dodo.model.entities.Size;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface SizeMapper extends BaseMapper<Size, SizeDto> {
}
