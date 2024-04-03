package Final.Project.dodo.service.impl;


import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.OrderProductRep;
import Final.Project.dodo.model.dto.OrderProductDto;
import Final.Project.dodo.model.entities.OrderProduct;
import Final.Project.dodo.model.mapper.OrderProductMapper;
import Final.Project.dodo.model.request.create.OrderProductCreateRequest;
import Final.Project.dodo.model.request.update.OrderProductUpdateRequest;
import Final.Project.dodo.service.OrderProductService;
import Final.Project.dodo.service.ProductService;
import Final.Project.dodo.service.ProductSizeService;
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
    public OrderProductDto create(OrderProductCreateRequest request) {
        OrderProductDto dto = new OrderProductDto();
        dto.setOrder(request.getDto());
        dto.setProduct(productService.findById(request.getProductId()));
        dto.setPrice(productSizeService.findByProductIdAndSizeId(request.getProductId(), request.getSizeId()).getPrice());
        return save(dto);
    }

    @Override
    public OrderProductDto update(OrderProductUpdateRequest request) {
        OrderProductDto dto = new OrderProductDto();
        dto.setId(request.getId());
        dto.setOrder(request.getDto());
        dto.setProduct(productService.findById(request.getProductId()));
        dto.setPrice(productSizeService.findByProductId(request.getProductId()).getPrice());
        return update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return delete(findById(id));
    }

    @Override
    public List<OrderProductDto> findAllByOrderId(Long id) {
        return mapper.toDtos(rep.findAllByOrderId(id), context);
    }
}
