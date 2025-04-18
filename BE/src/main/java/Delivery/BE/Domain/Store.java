package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100) // 가게 이름
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false) // Member 와 연결
    private Member member;

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

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "store_category",   // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "store_id"),  // Store 와의 관계
            inverseJoinColumns = @JoinColumn(name = "category_id")  // Category 와의 관계
    )
    private Set<Category> categories;

    @ManyToMany(mappedBy = "favoriteStores")  // Member 엔티티와의 관계
    private Set<Member> favoriteMembers;

    @Column(name = "created_at", updatable = false) // 가게 생성 시각
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at") // 가게 업데이트 시각
    @UpdateTimestamp
    private Timestamp updatedAt;

    public enum Status {
        OPEN,
        CLOSED,
        TEMPORARILY_CLOSED
    }
}