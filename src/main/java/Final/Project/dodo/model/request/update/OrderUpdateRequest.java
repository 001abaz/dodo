package Final.Project.dodo.model.request.update;

import Final.Project.dodo.model.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdateRequest {
    Long id;
    BigDecimal totalPrice;
    BigDecimal dodoCoins;
    LocalDateTime orderDate;
    OrderStatus orderStatus;
    Boolean paymentType;
    BigDecimal discount;
    Long userId;
    Long addressId;
}
