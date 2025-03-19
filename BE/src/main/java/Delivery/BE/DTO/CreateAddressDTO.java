package Delivery.BE.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAddressDTO {
    @NotBlank(message = "주소는 빈칸일 수 없습니다.")
    private String address;

    private String alias;

    private String detailAddress;
}
