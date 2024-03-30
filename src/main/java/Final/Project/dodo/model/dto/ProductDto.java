package Final.Project.dodo.model.dto;

import Final.Project.dodo.base.BaseDto;
import Final.Project.dodo.model.entities.Categories;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class ProductDto extends BaseDto {
    String name;
    String logo;
    String description;
    CategoriesDto categories;
}
