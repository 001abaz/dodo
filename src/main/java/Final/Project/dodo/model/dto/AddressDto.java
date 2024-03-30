package Final.Project.dodo.model.dto;



import Final.Project.dodo.base.BaseDto;
import Final.Project.dodo.model.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class AddressDto extends BaseDto {
    String street;
    String num;
    String comment;
    Status status;
    UserDto user;
}
