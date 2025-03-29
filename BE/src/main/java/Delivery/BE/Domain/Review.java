package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false) // Member 와 연결
    private Member member;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // Store 와 연결
    private Order order;

    @Column(name = "rating", nullable = false) // 리뷰 평점
    private int rating;

    @Column(name = "comment", length = 500) // 리뷰 내용
    private String comment;

    @Column(name = "created_at", updatable = false) // 리뷰 생성 시각
    @CreationTimestamp
    private Timestamp createdAt;
}
