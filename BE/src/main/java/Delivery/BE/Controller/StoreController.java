package Delivery.BE.Controller;

import Delivery.BE.DTO.StoreDTO;
import Delivery.BE.Service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("")
    public ResponseEntity<?> createStore(@Valid @RequestBody StoreDTO storeDTO) {
        storeService.createStore(storeDTO);
        return ResponseEntity.ok("가게 생성 완료");
    }
}
