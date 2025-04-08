package Delivery.BE.Service;

import Delivery.BE.DTO.RequestTossPaymentDTO;
import Delivery.BE.DTO.CreateTossPaymentDTO;
import Delivery.BE.Domain.Order;
import Delivery.BE.Domain.TossPayment;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.InformationNotMatchException;
import Delivery.BE.Repository.TossPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TossPaymentService {
    private final TossPaymentRepository tossPaymentRepository;
    private final OrderService orderService;
    private final WebClient tossWebClient;

    public void confirmTossPayment(RequestTossPaymentDTO requestTossPaymentDTO) {
        CreateTossPaymentDTO response = tossWebClient.post()
                .uri("/payments/confirm")
                .bodyValue(Map.of(
                        "paymentKey", requestTossPaymentDTO.getPaymentKey(),
                        "orderId", requestTossPaymentDTO.getOrderId(),
                        "amount", requestTossPaymentDTO.getAmount()
                ))
                .retrieve()
                .bodyToMono(CreateTossPaymentDTO.class)
                .block(); // 동기 방식

        Order order = validateTossPayment(Objects.requireNonNull(response).getOrderId(), response.getTotalAmount());

        TossPayment tossPayment = TossPayment.builder()
                .tossPaymentKey(response.getPaymentKey())
                .order(order)
                .paymentStatus(response.getStatus())
                .paymentMethod(response.getMethod())
                .tossOrderId(response.getOrderId())
                .totalAmount(response.getTotalAmount())
                .approvedAt(response.getApprovedAt())
                .requestedAt(response.getRequestedAt())
                .build();

        tossPaymentRepository.save(tossPayment);
    }

    private Order validateTossPayment(String tossOrderId, int tossTotalAmount) {
        String[] parts = tossOrderId.split("_");
        if (parts.length != 2) throw new ForbiddenException("orderId가 예상치 못한 값입니다.");
        Long id = Long.parseLong(parts[1]);
        Order order = orderService.findOrderById(id);

        if (tossTotalAmount != order.getTotalAmount())
            throw new InformationNotMatchException("결제 금액과 주문 금액이 다릅니다.");

        return order;
    }
}
