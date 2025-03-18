package Delivery.BE.DTO;

import Delivery.BE.Domain.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateStoreDTO {
    @NotBlank(message = "이름은 빈 칸일 수 없습니다.")
    private String name;

    private String description;

    @NotBlank(message = "전화번호는 빈 칸일 수 없습니다.")
    private String phone;

    @NotBlank(message = "주소는 빈 칸일 수 없습니다.")
    private String address;

    private Store.Status status;

    @NotBlank(message = "영업시간은 빈 칸일 수 없습니다.")
    private String openingHours;

    private String logoUrl;
}
