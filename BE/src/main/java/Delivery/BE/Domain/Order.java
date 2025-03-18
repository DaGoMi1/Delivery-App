package Delivery.BE.Domain;

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
    private Long id;

    @Column(name = "order_date", updatable = false) // 주문 시각
    private Timestamp orderDate;

    @Column(name = "total_amount", nullable = false) // 주문 총 가격
    private int totalAmount;

    @Column(name = "payment_method") // 주문 결제 방법
    private String paymentMethod;

    @Column(name = "special_instructions") // 주문 요청사항
    private String specialInstructions;

    @Column(name = "status", nullable = false) // 주문 상태
    private Status status;

    @Column(name = "member_id", nullable = false) // Member 와 연결
    private Long memberId;

    @Column(name = "member_address_id", nullable = false) // Member_Address 와 연결
    private Long memberAddressId;

    public enum Status {
        PENDING,
        COMPLETED,
        CANCELLED
    }
}
