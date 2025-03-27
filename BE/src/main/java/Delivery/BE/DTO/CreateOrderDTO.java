package Delivery.BE.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrderDTO {
    @NotBlank(message = "결제 방법은 빈칸일 수 없습니다.")
    private String paymentMethod;

    private String specialInstructions;
}
