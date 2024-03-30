package Final.Project.dodo.model.dto;

import Final.Project.dodo.base.BaseDto;
import Final.Project.dodo.model.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class OrderDto extends BaseDto {
    BigDecimal totalPrice;
    BigDecimal dodoCoins;
    LocalDateTime orderDate;
    OrderStatus orderStatus;
    Boolean paymentType;
    BigDecimal discount;
    UserDto user;
    AddressDto address;
}
