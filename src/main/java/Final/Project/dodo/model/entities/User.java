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
@Table(name="tb_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;
    @Column(name = "dodo_coins")
    Integer dodoCoins;
    @Column(name = "temp_password")
    String temp_password;
    @Column(name = "password")
    String password;
}
