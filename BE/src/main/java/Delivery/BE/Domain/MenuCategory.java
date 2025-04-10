package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "menu_category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuCategory {
    @EmbeddedId
    private MenuCategoryId menuCategoryId;
}
