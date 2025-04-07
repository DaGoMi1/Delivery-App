package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateOrderDTO;
import Delivery.BE.DTO.ResponseOrderDTO;
import Delivery.BE.DTO.UpdateOrderDTO;
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
    public ResponseEntity<?> getAllMemberOrders() {
        List<ResponseOrderDTO> orderDTOList = orderService.getOrdersByMember();
        return ResponseEntity.ok(orderDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderByStoreId(@PathVariable Long id) {
        List<ResponseOrderDTO> orderDTOList = orderService.getOrdersByStore(id);
        return ResponseEntity.ok(orderDTOList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderDTO updateOrderDTO) {
        orderService.updateOrderStatus(id, updateOrderDTO);
        return ResponseEntity.ok("주문 상태 변경 완료");
    }
}
