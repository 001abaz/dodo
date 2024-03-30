package Final.Project.dodo.service;

import Final.Project.dodo.base.BaseService;
import Final.Project.dodo.model.dto.CategoriesDto;
import Final.Project.dodo.model.dto.ProductDto;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.request.create.CategoriesCreateRequest;
import Final.Project.dodo.model.request.create.ProductCreateRequest;
import Final.Project.dodo.model.request.update.CategoriesUpdateRequest;
import Final.Project.dodo.model.request.update.ProductUpdateRequest;

public interface ProductService extends BaseService<ProductDto> {
    ProductDto create(ProductCreateRequest request);

    ProductDto update(ProductUpdateRequest request);

    Boolean delete(Long id);
}
