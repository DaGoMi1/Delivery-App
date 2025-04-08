package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "toss_payment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TossPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "toss_order_id", nullable = false, unique = true)
    private String tossOrderId;

    @Column(name = "toss_payment_key", nullable = false, unique = true)
    private String tossPaymentKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "toss_payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "toss_payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "total_amount", nullable = false)
    private int totalAmount;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

    @Getter
    public enum PaymentMethod {
        VIRTUAL_ACCOUNT("가상계좌"),
        SIMPLE_PAYMENT("간편결제"),
        GAME_GIFT("게임문화상품권"),
        BANK_TRANSFER("계좌이체"),
        BOOK_GIFT("도서문화상품권"),
        CULTURE_GIFT("문화상품권"),
        CARD("카드"),
        MOBILE("휴대폰");

        private final String label;

        PaymentMethod(String label) {
            this.label = label;
        }
    }

    public enum PaymentStatus {
        ABORTED,
        CANCELED,
        DONE,
        EXPIRED,
        IN_PROGRESS,
        PARTIAL_CANCELED,
        READY,
        WAITING_FOR_DEPOSIT
    }
}
