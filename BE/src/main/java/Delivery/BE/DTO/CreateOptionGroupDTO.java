package Delivery.BE.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOptionGroupDTO {
    @NotNull(message = "menuId는 빈칸일 수 없습니다.")
    private Long menuId;

    @NotBlank(message = "이름은 빈칸일 수 없습니다.")
    private String name; // 옵션 이름 ex) 맵기 단계, 세트 유무, 양
}
