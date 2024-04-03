package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.dao.projection.OrderListResponse;
import Final.Project.dodo.model.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRep extends BaseRep<Order> {

    @Query("SELECT o.id as id, o.totalPrice as totalPrice, o.dodoCoins as dodocoins, o.orderDate as orderDate, o.orderStatus as orderStatus, o.paymentType as paymentType, o.discount, o.user.id as userId, o.address.id as addressId " +
            "FROM Order o " +
            "WHERE o.user.id = :userId")
    List<OrderListResponse> findByUserId(Long userId);

}
