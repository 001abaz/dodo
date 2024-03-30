package Final.Project.dodo.model.entities;

import Final.Project.dodo.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="tb_size")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Size extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "active")
    Boolean active;
}
