package Delivery.BE.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestTossPaymentDTO {
    @NotBlank(message = "paymentKey는 빈칸일 수 없습니다.")
    private String paymentKey;

    @NotBlank(message = "orderId는 빈칸일 수 없습니다.")
    private String orderId;

    @Min(value = 1, message = "amount는 1 이상이어야 합니다.")
    private int amount;
}
