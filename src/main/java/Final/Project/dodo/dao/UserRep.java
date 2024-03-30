package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.dao.projection.UserGetProjection;
import Final.Project.dodo.model.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRep extends BaseRep<User> {
    @Query(value = "select exists(select 1 from tb_user " +
                    " where email = :email)", nativeQuery = true)
    Boolean checkByEmail(String email);
    @Query(value = "SELECT u.id as id, u.name as name, u.phone as phone, u.email as email, u.password as password, " +
            "u.temp_password as temp_password, u.status as status, u.update_date as update_date, u.add_date as add_date, " +
            "u.dodo_coins as dodo_coins " +
            "FROM tb_user u " +
            "WHERE u.email = :email", nativeQuery = true)
    Optional<UserGetProjection> findByEmail(@Param("email") String email);

//    @Query("SELECT u FROM User u WHERE u.email = ?1")
//    List<User> findByEmail(String email);
}

