package Delivery.BE.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleUpgradeDTO {
    @NotBlank(message = "ownerCode를 입력해주세요.")
    private String ownerCode;
}
