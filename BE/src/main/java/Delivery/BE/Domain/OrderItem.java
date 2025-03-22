package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "order_items")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false) // 주문 수량
    private int quantity;

    @Column(name = "order_id", nullable = false) // 주문과 연결
    private Long orderId;

    @Column(name = "menu_id", nullable = false) // 메뉴와 연결
    private Long menuId;

    @Column(name = "created_at", updatable = false) // 주문 생성 시각
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at") // 주문 업데이트 시각
    @UpdateTimestamp
    private Timestamp updatedAt;
}
