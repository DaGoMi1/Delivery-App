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
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;            // 리뷰 ID

    @Column(name = "customer_id", nullable = false)
    private Long customerId;          // 사용자 ID (Review 테이블과 연결)

    @Column(name = "store_id", nullable = false)
    private Long storeId;             // 가게 ID (Review 테이블과 연결)

    @Column(name = "rating", nullable = false)
    private Double rating;            // 평점 (1.0 ~ 5.0)

    @Column(name = "comment", length = 500)
    private String comment;           // 리뷰 내용

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;      // 생성 시간

    @Column(name = "updated_at")
    private Timestamp updatedAt;      // 수정 시간

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
