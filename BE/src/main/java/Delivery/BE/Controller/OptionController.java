package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateOptionDTO;
import Delivery.BE.DTO.UpdateOptionDTO;
import Delivery.BE.Service.OptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @PostMapping("")
    public ResponseEntity<?> createOption(@Valid @RequestBody CreateOptionDTO createOptionDTO) {
        optionService.createOption(createOptionDTO);
        return ResponseEntity.ok("옵션 생성 완료");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOption(@PathVariable Long id, @RequestBody UpdateOptionDTO updateOptionDTO) {
        optionService.updateOption(id, updateOptionDTO);
        return ResponseEntity.ok("옵션 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return ResponseEntity.ok("옵션 삭제 완료");
    }
}
