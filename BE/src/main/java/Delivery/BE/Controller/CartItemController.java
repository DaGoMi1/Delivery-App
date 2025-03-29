package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateCartItemDTO;
import Delivery.BE.Service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-item")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping("")
    public ResponseEntity<?> addCartItem(@Valid @RequestBody CreateCartItemDTO createCartItemDTO) {
        cartItemService.addCartItem(createCartItemDTO);
        return ResponseEntity.ok("장바구니에 메뉴 추가 완료"); // 메뉴의 옵션까지 포함해서 저장
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok("장바구니에 메뉴 삭제 완료");
    }
}
