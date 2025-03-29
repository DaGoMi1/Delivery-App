package Delivery.BE.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateFavoriteDTO {
    @NotNull(message = "storeId는 빈칸일 수 없습니다.")
    private Long storeId;
}
