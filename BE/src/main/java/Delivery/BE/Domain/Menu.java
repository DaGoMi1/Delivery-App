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
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;                // 메뉴 ID

    @Column(name = "store_id", nullable = false)
    private Long storeId;               // 가게 ID (Menu 테이블과 연결)

    @Column(name = "name", nullable = false, length = 100)
    private String name;                // 메뉴 이름

    @Column(name = "description")
    private String description;         // 메뉴 설명

    @Column(name = "price", nullable = false)
    private Double price;               // 가격

    @Column(name = "category")
    private String category;            // 카테고리

    @Column(name = "image_url")
    private String imageUrl;            // 메뉴 이미지 URL

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;        // 메뉴 이용 가능 여부

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;        // 생성 시간

    @Column(name = "updated_at")
    private Timestamp updatedAt;        // 수정 시간

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
