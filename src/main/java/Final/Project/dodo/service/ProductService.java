package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.ProductDto;
import Final.Project.dodo.model.request.create.ProductCreateRequest;
import Final.Project.dodo.model.request.update.ProductUpdateRequest;

public interface ProductService extends BaseService<ProductDto> {
    String create(ProductCreateRequest request, Integer languageOrdinal);

    ProductDto update(ProductUpdateRequest request, Integer languageOrdinal);

    Boolean delete(Long id, Integer languageOrdinal);
}
