package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.model.dto.OrderProductDto;
import Final.Project.dodo.model.entities.OrderProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRep extends BaseRep<OrderProduct> {
    List<OrderProductDto> findAllByOrderId(Long id);
}
