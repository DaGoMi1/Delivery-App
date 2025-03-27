package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", updatable = false) // 주문 시각
    @CreationTimestamp
    private Timestamp orderDate;

    @Column(name = "total_amount", nullable = false) // 주문 총 가격
    private int totalAmount;

    @Column(name = "payment_method") // 주문 결제 방법
    private String paymentMethod;

    @Column(name = "special_instructions") // 주문 요청사항
    private String specialInstructions;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false) // 주문 상태
    private Status status;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false) // Member 와 연결
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false) // Member 와 연결
    private Store store;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    public enum Status {
        PENDING,
        CONFIRMED,
        PREPARING,
        OUT_FOR_DELIVERY,
        COMPLETED,
        CANCELLED
    }
}
