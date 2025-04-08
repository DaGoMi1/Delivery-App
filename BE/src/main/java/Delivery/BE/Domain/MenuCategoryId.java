package Delivery.BE.Domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MenuCategoryId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuCategoryId that = (MenuCategoryId) o;
        return Objects.equals(menu.getId(), that.menu.getId()) &&
                Objects.equals(category.getId(), that.category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu.getId(), category.getId());
    }
}
