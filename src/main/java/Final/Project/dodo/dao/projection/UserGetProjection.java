package Final.Project.dodo.dao.projection;

import Final.Project.dodo.model.enums.Status;

import java.time.LocalDateTime;

public interface UserGetProjection {
    Long getId();
    LocalDateTime getAddDate(); // Corrected method name
    LocalDateTime getUpdateDate(); // Corrected method name
    Status getStatus();

    String getName();
    String getEmail();
    String getPhone();
    Integer getDodoCoins();
    String getTemp_password();
    String getPassword();
}

