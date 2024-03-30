package Final.Project.dodo.model.request.create;

import Final.Project.dodo.model.dto.OrderDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderProductCreateRequest {
    Long productId;
    OrderDto dto;
    Long sizeId;
}
