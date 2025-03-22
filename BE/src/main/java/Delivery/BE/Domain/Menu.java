package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;               // Store 와 연결

    @Column(name = "name", nullable = false, length = 100)
    private String name;                // 메뉴 이름

    @Column(name = "description")
    private String description;         // 메뉴 설명

    @Column(name = "price", nullable = false)
    private int price;               // 메뉴 가격

    @Column(name = "image_url")
    private String imageUrl;            // 메뉴 이미지 경로

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;        // 메뉴 이용 가능 여부

    @ManyToMany
    @JoinTable(
            name = "menu_category",   // 중간 테이블 이름
            joinColumns = @JoinColumn(name = "menu_id"),  // Menu 와의 관계
            inverseJoinColumns = @JoinColumn(name = "category_id")  // Category 와의 관계
    )
    private Set<Category> categories;
}
