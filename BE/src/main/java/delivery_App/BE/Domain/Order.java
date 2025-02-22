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
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;           // 주문 ID

    @Column(name = "customer_id", nullable = false)
    private Long customerId;        // 사용자 ID

    @Column(name = "store_id", nullable = false)
    private Long storeId;           // 가게 ID

    @Column(name = "order_date", nullable = false)
    private Timestamp orderDate;    // 주문 시간

    @Column(name = "status", nullable = false)
    private String status;          // 주문 상태 (PENDING, COMPLETED, CANCELLED)

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;     // 총 금액

    @Column(name = "payment_method")
    private String paymentMethod;    // 결제 방법

    @Column(name = "delivery_address")
    private String deliveryAddress;  // 배달 주소

    @Column(name = "special_instructions")
    private String specialInstructions; // 추가 메모
}
