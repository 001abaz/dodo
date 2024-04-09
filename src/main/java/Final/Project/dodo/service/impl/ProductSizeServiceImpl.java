package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.ProductSizeRep;
import Final.Project.dodo.dao.projection.ProductSizeResponse;
import Final.Project.dodo.exception.NotFoundException;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.entities.ProductSize;
import Final.Project.dodo.model.enums.Status;
import Final.Project.dodo.model.mapper.ProductSizeMapper;
import Final.Project.dodo.model.request.create.ProductSizeCreateRequest;
import Final.Project.dodo.model.request.update.ProductSizeUpdateRequest;
import Final.Project.dodo.service.ProductService;
import Final.Project.dodo.service.ProductSizeService;
import Final.Project.dodo.service.SizeService;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductSizeServiceImpl extends BaseServiceImpl<ProductSize, ProductSizeRep, ProductSizeDto, ProductSizeMapper> implements ProductSizeService {
    public ProductSizeServiceImpl(ProductSizeRep rep, ProductSizeMapper mapper, ProductService productService, SizeService sizeService) {
        super(rep, mapper);
        this.productService = productService;
        this.sizeService = sizeService;
    }

    private final ProductService productService;
    private final SizeService sizeService;

    @Override
    public String create(ProductSizeCreateRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        ProductSizeDto dto = new ProductSizeDto();
        dto.setProduct(productService.findById(request.getProductId(), languageOrdinal));
        dto.setSize(sizeService.findById(request.getSizeId(), languageOrdinal));
        dto.setPrice(request.getPrice());
        dto.setActive(request.getActive());
        save(dto);
        return ResourceBundleLanguage.periodMessage(language,"createSuccessful");
    }


    @Override
    public ProductSizeDto update(ProductSizeUpdateRequest request, Integer languageOrdinal) {
        ProductSizeDto dto = findById(request.getId(), languageOrdinal);
//        dto.setAddDate(dto.getAddDate());
        dto.setAddDate(LocalDateTime.now());
        dto.setProduct(productService.findById(request.getProductId(), languageOrdinal));
        dto.setSize(sizeService.findById(request.getSizeId(), languageOrdinal));
        dto.setPrice(request.getPrice());
        dto.setActive(request.getActive());
        return update(dto);
    }

    @Override
    public Boolean delete(Long id, Integer languageOrdinal) {
        return delete(findById(id, languageOrdinal), languageOrdinal);
    }

    @Override
    public ProductSizeDto findByProductId(Long id) {
        Language language = Language.getLanguage(null);
        try {
            return rep.findByProductId(id);
        }catch (NotFoundException n){
            throw new NotFoundException(ResourceBundleLanguage.periodMessage(language,"objectNotFound"));
        }
    }
    @Override
    public ProductSizeDto findByProductIdAndSizeId(Long productId, Long sizeId, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        if (productId == null || sizeId == null) {
            throw new NotFoundException(ResourceBundleLanguage.periodMessage(language, "objectNotFound"));
        }

        ProductSizeResponse response = rep.findByProductIdAndSizeId(productId, sizeId);
        if (response == null) {
            throw new NotFoundException(ResourceBundleLanguage.periodMessage(language, "objectNotFound"));
        }

        ProductSizeDto dto = new ProductSizeDto();
        dto.setId(response.getId());
        dto.setSize(sizeService.findById(sizeId, languageOrdinal));
        dto.setProduct(productService.findById(productId, languageOrdinal));
        dto.setPrice(response.getPrice());
        dto.setActive(response.getActive());
        dto.setStatus(Status.valueOf(response.getStatus()));
        return dto;
    }

    @Override
    public List<ProductSizeDto> getProductSizes(Long sizeId, BigDecimal fromPrice,
                                                BigDecimal toPrice, String name,
                                                Long categoryId, Pageable pageable) {

        return mapper.toDtos(rep.findByFilter(
                sizeId, fromPrice, toPrice, name, categoryId, pageable).getContent(), context);
    }


}
