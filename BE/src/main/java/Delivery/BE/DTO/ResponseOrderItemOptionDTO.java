package Delivery.BE.DTO;

import Delivery.BE.Domain.OrderItemOption;
import lombok.Data;

@Data
public class ResponseOrderItemOptionDTO {
    private String optionGroupName;
    private String optionName;
    private int optionPrice;

    public ResponseOrderItemOptionDTO(OrderItemOption orderItemOption) {
        this.optionGroupName = orderItemOption.getOptionGroupName();
        this.optionName = orderItemOption.getOptionName();
        this.optionPrice = orderItemOption.getOptionPrice();
    }
}
