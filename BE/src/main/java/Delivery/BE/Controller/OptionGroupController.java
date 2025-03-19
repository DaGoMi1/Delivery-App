package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateOptionGroupDTO;
import Delivery.BE.Service.OptionGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
