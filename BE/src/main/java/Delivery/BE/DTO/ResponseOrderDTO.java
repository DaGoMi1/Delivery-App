package Delivery.BE.DTO;

import Delivery.BE.Domain.Order;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseOrderDTO {
    private String storeName;
    private String storePhone;
    private String storeAddress;

    private String memberAddress;
    private String memberDetailAddress;

    private Timestamp orderDate;
    private int totalAmount;
    private String paymentMethod;
    private String specialInstructions;

    private Order.Status status;

    private List<ResponseOrderItemDTO> orderItems;

    public ResponseOrderDTO(Order order) {
        this.storeName = order.getStore().getName();
        this.storePhone = order.getStore().getPhone();
        this.storeAddress = order.getStore().getAddress();

        this.memberAddress = order.getAddress();
        this.memberDetailAddress = order.getDetailAddress();

        this.orderDate = order.getOrderDate();
        this.totalAmount = order.getTotalAmount();
        this.paymentMethod = order.getPaymentMethod();
        this.specialInstructions = order.getSpecialInstructions();

        this.status = order.getStatus();

        this.orderItems = order.getOrderItems().stream()
                .map(ResponseOrderItemDTO::new)
                .collect(Collectors.toList());
    }
}
