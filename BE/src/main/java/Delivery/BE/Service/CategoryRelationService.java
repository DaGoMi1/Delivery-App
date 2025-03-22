package Delivery.BE.Service;

import Delivery.BE.DTO.MenuCategoryDTO;
import Delivery.BE.Domain.*;
import Delivery.BE.Exception.AlreadyRegisteredException;
import Delivery.BE.Repository.MenuCategoryRepository;
import Delivery.BE.Repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryRelationService {
    private final CategoryService categoryService;
    private final MenuService menuService;
    private final MenuCategoryRepository menuCategoryRepository;
    private final StoreCategoryRepository storeCategoryRepository;

    @Transactional
    public void addCategoryInMenu(MenuCategoryDTO menuCategoryDTO) {
        Long menuId = menuCategoryDTO.getMenuId();
        Long categoryId = menuCategoryDTO.getCategoryId();

        Menu menu = menuService.findMenuById(menuId);
        Category category = categoryService.findCategoryById(categoryId);

        // 메뉴 소유자 검증
        menuService.checkMenuOwner(menu);

        // 메뉴-카테고리 복합키 생성
        MenuCategoryId menuCategoryId = new MenuCategoryId(menu, category);

        if(checkCategoryInMenu(menuCategoryId)) throw new AlreadyRegisteredException("메뉴에 이미 추가된 카테고리 입니다.");

        MenuCategory menuCategory = MenuCategory.builder()
                .menuCategoryId(menuCategoryId)
                .build();

        menuCategoryRepository.save(menuCategory);

        Store store = menu.getStore(); // 가게-카테고리 복합키 생성
        StoreCategoryId storeCategoryId = new StoreCategoryId(store, category);

        if (!checkCategoryInStore(storeCategoryId)){ // 가게-카테고리가 연결되어 있지 않다면
            addCategoryInStore(storeCategoryId); // 가게-카테고리 연결
        }
    }

    private boolean checkCategoryInMenu(MenuCategoryId menuCategoryId) {
        return menuCategoryRepository.existsByMenuCategoryId(menuCategoryId);
    }

    private boolean checkCategoryInStore(StoreCategoryId storeCategoryId) {
        return storeCategoryRepository.existsByStoreCategoryId(storeCategoryId);
    }

    private void addCategoryInStore(StoreCategoryId storeCategoryId) {
        StoreCategory storeCategory = StoreCategory.builder()
                .storeCategoryId(storeCategoryId)
                .build();

        storeCategoryRepository.save(storeCategory);
    }
}
