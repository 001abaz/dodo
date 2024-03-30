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
@Table(name="tb_categories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Categories extends BaseEntity {

    @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "definition")
    String definition;
}
