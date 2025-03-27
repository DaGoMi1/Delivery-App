package Delivery.BE.DTO;

import Delivery.BE.Domain.OrderItem;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseOrderItemDTO {
    private String menuName;
    private String menuDescription;
    private int menuPrice;

    private int quantity;

    private List<ResponseOrderItemOptionDTO> options;

    public ResponseOrderItemDTO(OrderItem orderItem) {
        this.menuName = orderItem.getMenuName();
        this.menuDescription = orderItem.getMenuDescription();
        this.menuPrice = orderItem.getMenuPrice();

        this.quantity = orderItem.getQuantity();

        this.options = orderItem.getOrderItemOptions().stream()
                .map(ResponseOrderItemOptionDTO::new)
                .collect(Collectors.toList());
    }
}
