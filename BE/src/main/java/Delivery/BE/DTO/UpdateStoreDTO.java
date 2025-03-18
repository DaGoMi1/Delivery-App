package Delivery.BE.DTO;

import Delivery.BE.Domain.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStoreDTO {
    @NotBlank(message = "id는 빈 칸일 수 없습니다.")
    private Long id;

    private String name;

    private String description;

    private String phone;

    private String address;

    private Store.Status status;

    private String openingHours;

    private String logoUrl;
}
