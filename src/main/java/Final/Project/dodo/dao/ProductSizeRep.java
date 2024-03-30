package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.entities.ProductSize;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRep extends BaseRep<ProductSize> {

    ProductSizeDto findByProductId(Long productId);

    @Query(value = "select p.id, p.price, p.product_id, p.size_id, p.status, p.active from tb_product_size p " +
            " where product_id = :productId and size_id = :sizeId", nativeQuery = true)
    ProductSizeDto findByProductIdAndSizeId(Long productId, Long sizeId);
}
