package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateMenuDTO;
import Delivery.BE.DTO.MenuCategoryDTO;
import Delivery.BE.Service.MenuCategoryService;
import Delivery.BE.Service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final MenuCategoryService menuCategoryService;

    @PostMapping("")
    public ResponseEntity<?> createMenu(@Valid @RequestBody CreateMenuDTO createMenuDTO) {
        menuService.createMenu(createMenuDTO);
        return ResponseEntity.ok("메뉴 생성 완료");
    }

    @PostMapping("/category")
    public ResponseEntity<?> menuCategory(@Valid @RequestBody MenuCategoryDTO menuCategoryDTO) {
        menuCategoryService.addCategoryInMenu(menuCategoryDTO);
        return ResponseEntity.ok("메뉴에 카테고리 추가 완료");
    }


}
