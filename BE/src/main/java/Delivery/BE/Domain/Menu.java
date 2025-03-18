package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "store_id", nullable = false)
    private Long storeId;               // Store 와 연결

    @Column(name = "name", nullable = false, length = 100)
    private String name;                // 메뉴 이름

    @Column(name = "description")
    private String description;         // 메뉴 설명

    @Column(name = "price", nullable = false)
    private Double price;               // 메뉴 가격

    @Column(name = "image_url")
    private String imageUrl;            // 메뉴 이미지 경로

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;        // 메뉴 이용 가능 여부

    @Column(name = "spicy_level")
    private int spicyLevel;        // 맵기 단계

    @Column(name = "size")
    private int size;        // 양 (소, 중, 대 등등)

    @ManyToMany
    @JoinTable(
            name = "menu_category",   // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "menu_id"),  // Menu 와의 관계
            inverseJoinColumns = @JoinColumn(name = "category_id")  // Category 와의 관계
    )
    private Set<Category> categories = new HashSet<>();
}
