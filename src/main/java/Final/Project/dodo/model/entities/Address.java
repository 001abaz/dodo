package Final.Project.dodo.model.entities;

import Final.Project.dodo.base.BaseEntity;
import Final.Project.dodo.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_address")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "street")
    String street;
    @Column(name = "num")
    String num;
    @Column(name = "comment")
    String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Override
    protected void onCreate() {
        super.onCreate();
        status = Status.ACTIVE;
    }
}
