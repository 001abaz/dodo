package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.model.dto.OrderDto;
import Final.Project.dodo.model.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRep extends BaseRep<Order> { }
