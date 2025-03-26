package Delivery.BE.DTO;

import Delivery.BE.Domain.Option;
import lombok.Data;

@Data
public class ResponseOptionDTO {
    private Long optionId;
    private String name;
    private int price;

    public ResponseOptionDTO(Option option) {
        this.optionId = option.getId();
        this.name = option.getName();
        this.price = option.getPrice();
    }
}
