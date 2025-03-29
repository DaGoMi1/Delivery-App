package Delivery.BE.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReviewDTO {
    @NotNull(message = "orderId는 빈칸일 수 없습니다.")
    private Long orderId;

    @Min(value = 1, message = "별점은 1점이 최소입니다.")
    @Max(value = 5, message = "별점은 5점이 최대입니다.")
    private  int rating;

    private String comment;
}
