package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.ProductRep;
import Final.Project.dodo.model.dto.ProductDto;
import Final.Project.dodo.model.entities.Product;
import Final.Project.dodo.model.mapper.ProductMapper;
import Final.Project.dodo.model.request.create.ProductCreateRequest;
import Final.Project.dodo.model.request.update.ProductUpdateRequest;
import Final.Project.dodo.service.CategoriesService;
import Final.Project.dodo.service.ProductService;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRep, ProductDto, ProductMapper> implements ProductService {
    public ProductServiceImpl(ProductRep rep, ProductMapper mapper, CategoriesService categoriesService) {
        super(rep, mapper);
        this.categoriesService = categoriesService;
    }
    private final CategoriesService categoriesService;

    @Override
    public String create(ProductCreateRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        ProductDto dto = new ProductDto();
        dto.setName(request.getName());
        dto.setDescription(request.getDescription());
        dto.setLogo(request.getLogo());
        dto.setCategories(categoriesService.findById(request.getCategoryId(), languageOrdinal));
        save(dto);
        return ResourceBundleLanguage.periodMessage(language,"createSuccessful");
    }


    @Override
    public ProductDto update(ProductUpdateRequest request, Integer languageOrdinal) {
        ProductDto dto = new ProductDto();
        dto.setId(request.getId());
        dto.setName(request.getName());
        dto.setDescription(request.getDescription());
        dto.setLogo(request.getLogo());
        dto.setCategories(categoriesService.findById(request.getCategoryId(), languageOrdinal));
        return update(dto);
    }

    @Override
    public Boolean delete(Long id, Integer languageOrdinal) {
        return delete(findById(id, languageOrdinal), languageOrdinal);
    }
}
