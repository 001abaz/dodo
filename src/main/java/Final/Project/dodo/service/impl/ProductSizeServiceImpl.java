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
//        dto.setAddDate(dto.getAddDate());
        dto.setAddDate(LocalDateTime.now());
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
        // Check if productId or sizeId is null
        if (productId == null || sizeId == null) {
            // Handle the case where productId or sizeId is null
            // For example, you can throw an exception or return null
            throw new IllegalArgumentException("productId and sizeId must not be null");
        }

        ProductSizeResponse response = rep.findByProductIdAndSizeId(productId, sizeId);
        if (response == null) {
            // Handle the case where no record is found for the given productId and sizeId
            // For example, you can throw an exception or return null
            throw new NotFoundException("Product size not found for productId=" + productId + " and sizeId=" + sizeId);
        }

        ProductSizeDto dto = new ProductSizeDto();
        dto.setId(response.getId());
        dto.setSize(sizeService.findById(sizeId));
        dto.setProduct(productService.findById(productId));
        dto.setPrice(response.getPrice());
        dto.setActive(response.getActive());
        dto.setStatus(Status.valueOf(response.getStatus()));
        return dto;
    }

    @Override
    public List<ProductSizeDto> getProductSizes(Long sizeId, BigDecimal fromPrice, BigDecimal toPrice, String name, Long categoryId, Pageable pageable) {

        return mapper.toDtos(rep.findByFilter(
                sizeId, fromPrice, toPrice, name, categoryId, pageable).getContent(), context);
    }


}
