package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.dao.projection.ProductSizeResponse;
import Final.Project.dodo.model.dto.ProductSizeDto;
import Final.Project.dodo.model.entities.ProductSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductSizeRep extends BaseRep<ProductSize> {

    ProductSizeDto findByProductId(Long productId);

    @Query(value = "SELECT p.id, p.price, p.product_id, p.size_id, p.status, p.active " +
            "FROM tb_product_size p " +
            "WHERE product_id = :productId " +
            "  AND size_id = :sizeId " +
            "  AND active = true " +
            "LIMIT 1", nativeQuery = true)
    ProductSizeResponse findByProductIdAndSizeId(Long productId, Long sizeId);

    @Query("SELECT ps FROM ProductSize ps " +
            "INNER JOIN ps.product p " +
            "WHERE (:sizeId IS NULL OR ps.size.id = :sizeId) " +
            "AND (:fromPrice IS NULL OR ps.price >= :fromPrice) " +
            "AND (:toPrice IS NULL OR ps.price <= :toPrice) " +
            "AND (:name IS NULL OR p.name LIKE %:name%) " +
            "AND (:categoryId IS NULL OR p.categories.id = :categoryId)")
    Page<ProductSize> findByFilter(Long sizeId, BigDecimal fromPrice, BigDecimal toPrice,
                                   String name, Long categoryId, Pageable pageable);
}
