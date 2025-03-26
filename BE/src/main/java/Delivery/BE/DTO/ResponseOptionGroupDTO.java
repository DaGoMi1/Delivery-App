package Delivery.BE.DTO;

import Delivery.BE.Domain.OptionGroup;
import lombok.Data;

@Data
public class ResponseOptionGroupDTO {
    private Long optionGroupId;
    private String name;

    public ResponseOptionGroupDTO(OptionGroup optionGroup) {
        this.optionGroupId = optionGroup.getId();
        this.name = optionGroup.getName();
    }
}
