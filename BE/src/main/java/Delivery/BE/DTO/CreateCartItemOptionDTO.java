package Delivery.BE.DTO;

import Delivery.BE.Domain.CartItem;
import Delivery.BE.Domain.Option;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCartItemOptionDTO {
    @NotNull(message = "option은 빈칸일 수 없습니다.")
    private final Option option;

    @NotNull(message = "cartItem은 빈칸일 수 없습니다.")
    private final CartItem cartItem;
}
