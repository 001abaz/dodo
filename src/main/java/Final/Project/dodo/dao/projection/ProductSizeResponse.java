package Final.Project.dodo.dao.projection;

import java.math.BigDecimal;

public interface ProductSizeResponse {
    Long getId();
    BigDecimal getPrice();
    Boolean getActive();
    Long getProductId();

    Long getSizeId();
    String getStatus();
}
