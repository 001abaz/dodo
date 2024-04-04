package Final.Project.dodo.dao.projection;

import Final.Project.dodo.model.enums.OrderStatus;
import Final.Project.dodo.model.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderListResponse {
    Long getId();
    BigDecimal getTotalPrice();
    Integer getDodoCoins();
    LocalDateTime getOrderDate();
    OrderStatus getOrderStatus();
    PaymentType getPaymentType();
    BigDecimal getDiscount();
    Long getUserId();
    Long getAddressId();
}
