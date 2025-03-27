package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateOrderDTO;
import Delivery.BE.DTO.ResponseOrderDTO;
import Delivery.BE.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        orderService.createOrder(createOrderDTO);
        return ResponseEntity.ok("주문 생성 완료");
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOrders() {
        List<ResponseOrderDTO> orderDTOList = orderService.getOrdersByMember();
        return ResponseEntity.ok(orderDTOList);
    }
}
