package Final.Project.dodo.dao;

import Final.Project.dodo.base.BaseRep;
import Final.Project.dodo.dao.projection.AddressListResponse;
import Final.Project.dodo.dao.projection.AddressResponse;
import Final.Project.dodo.model.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRep extends BaseRep<Address> {
    @Query("select a.id as id, concat(a.street,',',a.num ) " +
            "as address from Address a where a.user.id=:userId")
    List<AddressListResponse> findAllByUserId(Long userId);

    @Query("select a.id as id, a.street as street ,a.num as num  " +
            " from Address a where a.id=id")
    AddressResponse findByAddressId(Long id);
}
