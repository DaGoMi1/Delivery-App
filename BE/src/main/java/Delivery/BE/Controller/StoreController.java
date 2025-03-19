package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateStoreDTO;
import Delivery.BE.DTO.UpdateStoreDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Service.MemberService;
import Delivery.BE.Service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<?> createStore(@Valid @RequestBody CreateStoreDTO createStoreDTO) {
        Member member = memberService.getMemberInfo();
        storeService.createStore(createStoreDTO ,member);
        return ResponseEntity.ok("가게 생성 완료");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStore(@PathVariable Long id, @RequestBody UpdateStoreDTO updateStoreDTO) {
        storeService.updateStore(id, updateStoreDTO);
        return ResponseEntity.ok("가게 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("가게 삭제 완료");
    }
}
