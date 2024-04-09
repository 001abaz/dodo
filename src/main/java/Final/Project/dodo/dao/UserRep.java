package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.dao.projection.OrderListResponse;
import Final.Project.dodo.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends BaseRep<User> {

//    @Query(value = "SELECT u.id as id, u.name as name, u.phone as phone, u.email as email, u.password as password, " +
//            "u.temp_password as temp_password, u.status as status, u.update_date as update_date, u.add_date as add_date, " +
//            "u.dodo_coins as dodo_coins " +
//            "FROM tb_user u " +
//            "WHERE u.email = :email", nativeQuery = true)
//    Optional<UserGetProjection> findByEmail(@Param("email") String email);


    @Query("SELECT o.id as id, o.totalPrice as totalPrice, o.dodoCoins as dodocoins, " +
            " o.orderDate as orderDate,  o.orderStatus as orderStatus, o.paymentType as paymentType, " +
            " o.discount, o.user.id as userId, o.address.id as addressId " +
            "FROM Order o " +
            "WHERE o.user.id = :userId")
    Page<OrderListResponse> findByUserId(Long userId, Pageable pageable);

}

