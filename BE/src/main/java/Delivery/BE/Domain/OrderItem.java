package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false) // 주문 수량
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false) // Member 와 연결
    private Order order;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();

    @Column(name = "menu_name", nullable = false) // 주문 당시 메뉴 이름
    private String menuName;

    @Column(name = "menu_description") // 주문 당시 메뉴 설명
    private String menuDescription;

    @Column(name = "menu_price", nullable = false) // 주문 당시 메뉴 가격
    private int menuPrice;
}
