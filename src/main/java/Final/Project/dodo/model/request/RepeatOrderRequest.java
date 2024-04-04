package Final.Project.dodo.model.request;


import Final.Project.dodo.model.enums.PaymentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RepeatOrderRequest {
    Long id;
    LocalDateTime orderDate;
    PaymentType paymentType;
    Long addressId;
}
