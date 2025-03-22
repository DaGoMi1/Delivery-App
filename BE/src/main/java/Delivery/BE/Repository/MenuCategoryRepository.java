package Delivery.BE.Repository;

import Delivery.BE.Domain.MenuCategory;
import Delivery.BE.Domain.MenuCategoryId;
import Delivery.BE.Domain.StoreCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    boolean existsByMenuCategoryId(MenuCategoryId menuCategoryId);
}
