package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.request.create.ProductSizeCreateRequest;
import Final.Project.dodo.model.request.update.ProductSizeUpdateRequest;

public interface ProductSizeService extends BaseService<ProductSizeDto> {
    ProductSizeDto create(ProductSizeCreateRequest request);

    ProductSizeDto update(ProductSizeUpdateRequest request);

    Boolean delete(Long id);


    ProductSizeDto findByProductId(Long id);
    ProductSizeDto findByProductIdAndSizeId(Long productId, Long sizeId);

}
