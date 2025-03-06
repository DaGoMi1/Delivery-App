package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100) // 가게 이름
    private String name;

    @Column(name = "member_id", nullable = false) // Member 와 연결
    private Long memberId;

    @Column(name = "description") // 가게 설명
    private String description;

    @Column(name = "phone", length = 20) // 가게 전화번호
    private String phone;

    @Column(name = "address") // 가게 주소
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status") // 가게 운영 상태 (OPEN, CLOSED, TEMPORARILY_CLOSED)
    private Status status;

    @Column(name = "opening_hours", length = 50) // 가게 영업 시간
    private String openingHours;

    @Column(name = "logo_url") // 가게 로고 경로
    private String logoUrl;

    @Column(name = "rating") // 가게 평점
    private Double rating;

    @Column(name = "created_at", updatable = false) // 가게 생성 시각
    private Timestamp createdAt;

    @Column(name = "updated_at") // 가게 업데이트 시각
    private Timestamp updatedAt;

    @ManyToMany
    @JoinTable(
            name = "store_category",   // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "store_id"),  // Menu 와의 관계
            inverseJoinColumns = @JoinColumn(name = "category_id")  // Category 와의 관계
    )
    private Set<Category> categories = new HashSet<>();

    public enum Status {
        OPEN,
        CLOSED,
        TEMPORARILY_CLOSED
    }
}