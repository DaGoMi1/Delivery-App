package Delivery.BE.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuCategoryDTO {
    @NotNull(message = "menuId는 null일 수 없습니다.")
    private Long menuId;

    @NotNull(message = "categoryId는 null일 수 없습니다.")
    private Long categoryId;
}
