package Final.Project.dodo.base;

import Final.Project.dodo.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@MappedSuperclass
@Data
public abstract class BaseDto {
    protected Long id;
    protected LocalDateTime addDate;
    protected LocalDateTime updateDate;
    @Enumerated(EnumType.STRING)
    protected Status status;
}
