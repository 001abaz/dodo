package Final.Project.dodo.model.mapper;

import Final.Project.dodo.base.BaseMapper;
import Final.Project.dodo.model.dto.CategoriesDto;
import Final.Project.dodo.model.entities.Categories;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,componentModel = "spring")
public interface CategoriesMapper extends BaseMapper<Categories, CategoriesDto> {
}
