package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateOptionGroupDTO;
import Delivery.BE.DTO.UpdateOptionGroupDTO;
import Delivery.BE.Service.OptionGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option-group")
@RequiredArgsConstructor
public class OptionGroupController {
    private final OptionGroupService optionGroupService;

    @PostMapping("")
    public ResponseEntity<?> createOptionGroup(@Valid @RequestBody CreateOptionGroupDTO createOptionGroupDTO) {
        optionGroupService.createOptionGroup(createOptionGroupDTO);
        return ResponseEntity.ok("옵션 그룹 생성 완료");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOptionGroup(@PathVariable Long id, @RequestBody UpdateOptionGroupDTO updateOptionGroupDTO) {
        optionGroupService.updateOptionGroup(id, updateOptionGroupDTO);
        return ResponseEntity.ok("옵션 그룹 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOptionGroup(@PathVariable Long id) {
        optionGroupService.deleteOptionGroup(id);
        return ResponseEntity.ok("옵션 그룹 삭제 완료");
    }
}
