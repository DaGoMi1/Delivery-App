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
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true) // 카테고리 이름
    private String name;

    @Column(name = "image_url") // 카테고리 이미지 경로
    private String imageUrl;

    @ManyToMany(mappedBy = "categories")  // Menu 엔티티와의 관계
    private Set<Menu> menus = new HashSet<>();
}
