package Final.Project.dodo.model.dto;

import Final.Project.dodo.base.BaseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class SizeDto extends BaseDto {
    String name;
    Boolean active;
}
