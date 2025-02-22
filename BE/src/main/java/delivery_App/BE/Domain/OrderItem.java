package delivery_App.BE.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long orderItemId;         // 주문 항목 ID

    @Column(name = "order_id", nullable = false)
    private Long orderId;             // 주문 ID (Order 테이블과 연결)

    @Column(name = "menu_id", nullable = false)
    private Long menuId;              // 메뉴 ID (Menu 테이블과 연결)

    @Column(name = "quantity", nullable = false)
    private Integer quantity;          // 수량

    @Column(name = "price", nullable = false)
    private Double price;              // 가격

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;       // 생성 시간

    @Column(name = "updated_at")
    private Timestamp updatedAt;       // 수정 시간

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}