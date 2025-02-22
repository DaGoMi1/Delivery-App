package delivery_App.BE.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId; // store_id에 맞춘 필드 이름

    @Column(name = "name", nullable = false, length = 100)
    private String name;            // 가게 이름

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;           // owner_id (BIGINT 에 맞춤)

    @Column(name = "description")
    private String description;     // 가게 설명

    @Column(name = "phone", length = 20)
    private String phone;           // 전화번호

    @Column(name = "address")
    private String address;         // 주소

    @Column(name = "latitude")
    private Double latitude;        // 위도

    @Column(name = "longitude")
    private Double longitude;       // 경도

    @Column(name = "status", columnDefinition = "ENUM('OPEN', 'CLOSED', 'TEMPORARILY_CLOSED') DEFAULT 'OPEN'")
    private String status;          // 운영 상태 (OPEN, CLOSED, TEMPORARILY_CLOSED)

    @Column(name = "opening_hours", length = 50)
    private String openingHours;    // 영업 시간

    @Column(name = "logo_url")
    private String logoUrl;         // 로고 URL

    @Column(name = "category", length = 50)
    private String category;        // 카테고리

    @Column(name = "rating", precision = 2, scale = 1)
    private Double rating;          // 평점 (5점 만점)

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;    // 생성 시간

    @Column(name = "updated_at")
    private Timestamp updatedAt;    // 수정 시간

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}