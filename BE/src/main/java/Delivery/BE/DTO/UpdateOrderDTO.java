package Delivery.BE.DTO;

import Delivery.BE.Domain.Order;
import lombok.Data;

@Data
public class UpdateOrderDTO {
    private Order.Status status;
    private String cancelReason;
}
