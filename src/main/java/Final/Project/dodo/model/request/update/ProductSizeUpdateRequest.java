package Final.Project.dodo.model.request.update;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeUpdateRequest {
    Long id;
    BigDecimal price;
    Boolean active;
    Long productId;
    Long sizeId;
}
