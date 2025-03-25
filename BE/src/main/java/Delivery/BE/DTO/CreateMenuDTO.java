package Delivery.BE.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateMenuDTO {
    @NotNull(message = "storeId는 빈칸일 수 없습니다.")
    private Long storeId;               // Store 와 연결

    @NotBlank(message = "이름은 빈칸일 수 없습니다.")
    private String name;                // 메뉴 이름

    private String description;         // 메뉴 설명

    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    private int price;               // 메뉴 가격

    private String imageUrl;            // 메뉴 이미지 경로
}
