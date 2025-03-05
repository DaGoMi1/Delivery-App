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

    @Column(name = "name", nullable = false, length = 100)
    private String name;            // 가게 이름

    @Column(name = "member_id", nullable = false)
    private Long memberId;           // owner_id (BIGINT 에 맞춤)

    @Column(name = "description")
    private String description;     // 가게 설명

    @Column(name = "phone", length = 20)
    private String phone;           // 전화번호

    @Column(name = "address")
    private String address;         // 주소

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;          // 운영 상태 (OPEN, CLOSED, TEMPORARILY_CLOSED)

    @Column(name = "opening_hours", length = 50)
    private String openingHours;    // 영업 시간

    @Column(name = "logo_url")
    private String logoUrl;         // 로고 URL

    @Column(name = "rating")
    private Double rating;          // 평점 (5점 만점)

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;    // 생성 시간

    @Column(name = "updated_at")
    private Timestamp updatedAt;    // 수정 시간

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