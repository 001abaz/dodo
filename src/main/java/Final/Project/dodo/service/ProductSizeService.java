package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.request.create.ProductSizeCreateRequest;
import Final.Project.dodo.model.request.update.ProductSizeUpdateRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductSizeService extends BaseService<ProductSizeDto> {
    String create(ProductSizeCreateRequest request, Integer languageOrdinal);

    ProductSizeDto update(ProductSizeUpdateRequest request, Integer languageOrdinal);

    Boolean delete(Long id, Integer languageOrdinal);

    ProductSizeDto findByProductId(Long id);
    ProductSizeDto findByProductIdAndSizeId(Long productId, Long sizeId, Integer languageOrdinal);
    List<ProductSizeDto> getProductSizes(Long sizeId, BigDecimal fromPrice, BigDecimal toPrice, String name, Long categoryId, Pageable pageable);

}
