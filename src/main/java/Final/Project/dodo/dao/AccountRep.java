package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.model.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRep extends BaseRep<Account> {
    @Query("select a from Account a " +
            "inner join User u on a.user = u " +
            "where a.email = :email ")
    Account findByEmail(@Param("email") String email);
}
