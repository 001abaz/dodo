package Final.Project.dodo.service.impl;

import Final.Project.dodo.base.BaseServiceImpl;
import Final.Project.dodo.dao.ProductSizeRep;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.entities.ProductSize;
import Final.Project.dodo.model.mapper.ProductSizeMapper;
import Final.Project.dodo.model.request.create.ProductSizeCreateRequest;
import Final.Project.dodo.model.request.update.ProductSizeUpdateRequest;
import Final.Project.dodo.service.ProductService;
import Final.Project.dodo.service.ProductSizeService;
import Final.Project.dodo.service.SizeService;
import org.springframework.stereotype.Service;

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
    public ProductSizeDto create(ProductSizeCreateRequest request) {
        ProductSizeDto dto = new ProductSizeDto();
        dto.setProduct(productService.findById(request.getProductId()));
        dto.setSize(sizeService.findById(request.getSizeId()));
        dto.setPrice(request.getPrice());
        dto.setActive(request.getActive());
        return save(dto);
    }


    @Override
    public ProductSizeDto update(ProductSizeUpdateRequest request) {
        ProductSizeDto dto = findById(request.getId());
        dto.setAddDate(dto.getAddDate());
        dto.setProduct(productService.findById(request.getProductId()));
        dto.setSize(sizeService.findById(request.getSizeId()));
        dto.setPrice(request.getPrice());
        dto.setActive(request.getActive());
        return update(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return delete(findById(id));
    }

    @Override
    public ProductSizeDto findByProductId(Long id) {
        return rep.findByProductId(id);
    }
    @Override
    public ProductSizeDto findByProductIdAndSizeId(Long productId, Long sizeId) {
        return rep.findByProductIdAndSizeId(productId, sizeId);
    }
}
