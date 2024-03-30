package Final.Project.dodo.model.request.create;

import Final.Project.dodo.model.entities.Product;
import Final.Project.dodo.model.entities.Size;
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
public class ProductSizeCreateRequest {
    BigDecimal price;
    Boolean active;
    Long productId;
    Long sizeId;
}
