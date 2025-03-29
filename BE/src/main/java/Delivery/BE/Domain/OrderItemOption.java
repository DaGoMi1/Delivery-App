package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "order_item_option")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_group_name", nullable = false) // 주문 당시 옵션 그룹 이름
    private String optionGroupName;

    @Column(name = "option_name", nullable = false) // 주문 당시 옵션 이름
    private String optionName;

    @Column(name = "option_price", nullable = false) // 주문 당시 옵션 가격
    private int optionPrice;

    @ManyToOne
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;
}
