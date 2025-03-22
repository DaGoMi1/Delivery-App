package Delivery.BE.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOptionDTO {
    @NotNull(message = "optionGroupId는 빈칸일 수 없습니다.")
    private Long optionGroupId;

    @NotBlank(message = "이름은 빈칸일 수 없습니다.")
    private String name; // 옵션 상세 이름 ex) 맵기 1단계, 단품, 大자

    @Min(value = 0, message = "가격은 0원 이상이여야 합니다.")
    private int price; // 옵션의 가격
}
