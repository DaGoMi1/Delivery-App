package Delivery.BE.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateCartItemDTO {
    @NotNull(message = "menuId는 빈칸일 수 없습니다.")
    private Long menuId;

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
    private int quantity;

    @NotNull(message = "추가 할 옵션이 없다면 빈 리스트를 보내주세요.")
    private List<Long> optionIds;
}
