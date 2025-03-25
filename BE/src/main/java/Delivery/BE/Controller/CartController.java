package Delivery.BE.Controller;

import Delivery.BE.DTO.ResponseCartDTO;
import Delivery.BE.Domain.CartItem;
import Delivery.BE.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    public ResponseEntity<?> getCartItemsList() { // 사용자의 장바구니에 저장된 데이터 조회
        ResponseCartDTO responseCartDTO = cartService.getCartItems();
        return ResponseEntity.ok(responseCartDTO);
    }
}
