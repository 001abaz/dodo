package Final.Project.dodo.model.entities;

import Final.Project.dodo.base.BaseEntity;
import Final.Project.dodo.model.enums.OrderStatus;
import Final.Project.dodo.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="tb_order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "total_price")
    BigDecimal totalPrice;

    @Column(name = "dodo_coins")
    BigDecimal dodoCoins;

    @Column(name = "order_date")
    LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    OrderStatus orderStatus;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    PaymentType paymentType;

    @Column(name = "discount")
    BigDecimal discount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    Address address;

}
