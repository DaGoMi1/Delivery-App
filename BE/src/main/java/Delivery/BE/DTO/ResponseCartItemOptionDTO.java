package Delivery.BE.DTO;

import Delivery.BE.Domain.Option;
import lombok.Data;

@Data
public class ResponseCartItemOptionDTO {
    private String optionGroupName; // 옵션 그룹 이름
    private String optionName; // 옵션 이름
    private int price; // 옵션 가격

    public ResponseCartItemOptionDTO(Option option) {
        this.optionName = option.getName();
        this.optionGroupName = option.getOptionGroup().getName();
        this.price = option.getPrice();
    }
}
