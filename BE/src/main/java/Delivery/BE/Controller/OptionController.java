package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateOptionDTO;
import Delivery.BE.Service.OptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
