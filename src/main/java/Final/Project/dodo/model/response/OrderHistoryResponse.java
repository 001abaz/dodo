package Final.Project.dodo.model.response;

import Final.Project.dodo.dao.projection.AddressResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class OrderHistoryResponse {
    Long numOfOrder;
    LocalDateTime orderDate;
    AddressResponse addressResponse;
    List<ProductListResponse> productListResponse;
    BigDecimal totalPrice;
}
