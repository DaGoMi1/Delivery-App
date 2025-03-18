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

    @PatchMapping("")
    public ResponseEntity<?> updateStore(@Valid @RequestBody UpdateStoreDTO updateStoreDTO) {
        storeService.updateStore(updateStoreDTO);
        return ResponseEntity.ok("가게 수정 완료");
    }
}
