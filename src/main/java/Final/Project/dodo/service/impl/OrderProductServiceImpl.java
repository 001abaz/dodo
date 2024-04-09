package Final.Project.dodo.service.impl;


import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.OrderProductRep;
import Final.Project.dodo.exception.NotFoundException;
import Final.Project.dodo.model.dto.OrderProductDto;
import Final.Project.dodo.model.entities.OrderProduct;
import Final.Project.dodo.model.mapper.OrderProductMapper;
import Final.Project.dodo.model.request.create.OrderProductCreateRequest;
import Final.Project.dodo.model.request.update.OrderProductUpdateRequest;
import Final.Project.dodo.service.OrderProductService;
import Final.Project.dodo.service.ProductService;
import Final.Project.dodo.service.ProductSizeService;
import Final.Project.dodo.utils.Language;
import Final.Project.dodo.utils.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl extends BaseServiceImpl<OrderProduct, OrderProductRep, OrderProductDto, OrderProductMapper> implements OrderProductService {
    public OrderProductServiceImpl(OrderProductRep rep, OrderProductMapper mapper, ProductService productService, ProductSizeService productSizeService) {
        super(rep, mapper);
        this.productService = productService;
        this.productSizeService = productSizeService;
    }
    private final ProductService productService;
    private final ProductSizeService productSizeService;


    @Override
    public String create(OrderProductCreateRequest request, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        OrderProductDto dto = new OrderProductDto();
        dto.setOrder(request.getDto());
        dto.setProduct(productService.findById(request.getProductId(), languageOrdinal));
        dto.setPrice(productSizeService.findByProductIdAndSizeId(request.getProductId(), request.getSizeId(), languageOrdinal).getPrice());
        save(dto);
        return ResourceBundleLanguage.periodMessage(language,"createSuccessful");
    }

    @Override
    public OrderProductDto update(OrderProductUpdateRequest request, Integer languageOrdinal) {
        OrderProductDto dto = new OrderProductDto();
        dto.setId(request.getId());
        dto.setOrder(request.getDto());
        dto.setProduct(productService.findById(request.getProductId(), languageOrdinal));
        dto.setPrice(productSizeService.findByProductId(request.getProductId()).getPrice());
        return update(dto);
    }

    @Override
    public Boolean delete(Long id, Integer languageOrdinal) {
        return delete(findById(id, languageOrdinal), languageOrdinal);
    }

    @Override
    public List<OrderProductDto> findAllByOrderId(Long id, Integer languageOrdinal) {
        Language language = Language.getLanguage(languageOrdinal);
        try {
            return mapper.toDtos(rep.findAllByOrderId(id), context);
        }catch (NotFoundException n){
            throw  new NotFoundException(ResourceBundleLanguage.periodMessage(language, "objectNotFound"));
        }

    }
}
