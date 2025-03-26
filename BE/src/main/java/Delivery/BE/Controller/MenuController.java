package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateMenuDTO;
import Delivery.BE.DTO.MenuCategoryDTO;
import Delivery.BE.DTO.ResponseMenuDTO;
import Delivery.BE.DTO.UpdateMenuDTO;
import Delivery.BE.Service.CategoryRelationService;
import Delivery.BE.Service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final CategoryRelationService categoryRelationService;

    @PostMapping("")
    public ResponseEntity<?> createMenu(@Valid @RequestBody CreateMenuDTO createMenuDTO) {
        menuService.createMenu(createMenuDTO);
        return ResponseEntity.ok("메뉴 생성 완료");
    }

    @PostMapping("/category")
    public ResponseEntity<?> menuCategory(@Valid @RequestBody MenuCategoryDTO menuCategoryDTO) {
        categoryRelationService.addCategoryInMenu(menuCategoryDTO);
        return ResponseEntity.ok("메뉴에 카테고리 추가 완료");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuByStoreId(@PathVariable Long id) {
        List<ResponseMenuDTO> menuDTOList = menuService.getMenusInStore(id);
        return ResponseEntity.ok(menuDTOList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable Long id, @RequestBody UpdateMenuDTO updateMenuDTO) {
        menuService.updateMenu(id, updateMenuDTO);
        return ResponseEntity.ok("메뉴 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok("메뉴 삭제 완료");
    }
}
