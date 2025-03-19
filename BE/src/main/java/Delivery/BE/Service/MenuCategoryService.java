package Delivery.BE.Service;

import Delivery.BE.DTO.MenuCategoryDTO;
import Delivery.BE.Domain.Category;
import Delivery.BE.Domain.Menu;
import Delivery.BE.Domain.MenuCategory;
import Delivery.BE.Repository.MenuCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuCategoryService {
    private final CategoryService categoryService;
    private final MenuService menuService;
    private final MenuCategoryRepository menuCategoryRepository;

    @Transactional
    public void addCategoryInMenu(MenuCategoryDTO menuCategoryDTO) {
        Long menuId = menuCategoryDTO.getMenuId();
        Long categoryId = menuCategoryDTO.getCategoryId();

        Menu menu = menuService.findMenuById(menuId);
        Category category = categoryService.findCategoryById(categoryId);

        MenuCategory menuCategory = MenuCategory.builder()
                .menu(menu)
                .category(category)
                .build();

        menuCategoryRepository.save(menuCategory);
    }
}
