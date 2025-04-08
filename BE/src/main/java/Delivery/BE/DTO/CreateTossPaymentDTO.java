package Delivery.BE.DTO;

import Delivery.BE.Domain.TossPayment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTossPaymentDTO {
    @NotBlank
    private String paymentKey;

    @NotBlank
    private String orderId;

    @NotBlank
    private TossPayment.PaymentStatus status;

    @NotBlank
    private TossPayment.PaymentMethod method;

    @NotNull
    private LocalDateTime approvedAt;

    @NotNull
    private LocalDateTime requestedAt;

    @Min(1)
    private int totalAmount;
}
