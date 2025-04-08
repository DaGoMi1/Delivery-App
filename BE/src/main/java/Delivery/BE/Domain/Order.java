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

    @Column(name = "cancel_reason")
    private String cancelReason;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false) // Member 와 연결
    private Member member;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false) // Member 와 연결
    private Store store;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private TossPayment tossPayment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    public enum Status {
        PENDING, // 사용자가 주문 요청한 상태
        CONFIRMED, // 가게가 주문을 확인한 상태
        PREPARING, // 가게가 배달 준비(요리 중)하는 상태
        OUT_FOR_DELIVERY, // 배달이 출발한 상태
        COMPLETED, // 배달 완료 된 상태
        CANCELLED; // 주문이 취소된 상태

        public boolean canTransitionTo(Status next) {
            if (next == CANCELLED) {
                // PREPARING전에만 CANCELLED로 갈 수 있도록 허용
                return this == PENDING || this == CONFIRMED;
            }

            return switch (this) {
                case PENDING -> next == CONFIRMED;
                case CONFIRMED -> next == PREPARING;
                case PREPARING -> next == OUT_FOR_DELIVERY;
                case OUT_FOR_DELIVERY -> next == COMPLETED;
                default -> false;
            };
        }
    }
}
