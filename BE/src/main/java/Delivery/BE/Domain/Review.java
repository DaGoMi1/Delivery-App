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
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false) // Member 와 연결
    private Long memberId;

    @Column(name = "store_id", nullable = false)  // Store 와 연결
    private Long storeId;

    @Column(name = "rating", nullable = false) // 리뷰 평점
    private Double rating;

    @Column(name = "comment", length = 500) // 리뷰 내용
    private String comment;

    @Column(name = "created_at", updatable = false) // 주문 생성 시각
    private Timestamp createdAt;

    @Column(name = "updated_at") // 주문 업데이트 시각
    private Timestamp updatedAt;
}
