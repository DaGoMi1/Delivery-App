package Delivery.BE.Controller;

import Delivery.BE.DTO.RequestTossPaymentDTO;
import Delivery.BE.Service.TossPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toss-payment")
@RequiredArgsConstructor
public class TossPaymentController {
    private final TossPaymentService tossPaymentService;

    @PostMapping("")
    public ResponseEntity<?> confirmTossPayment(@Valid @RequestBody RequestTossPaymentDTO tossPaymentDTO) {
        tossPaymentService.confirmTossPayment(tossPaymentDTO);
        return ResponseEntity.ok("결제 승인 완료");
    }
}
