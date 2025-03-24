package Delivery.BE.DTO;

import Delivery.BE.Domain.Store;
import lombok.Data;

@Data
public class UpdateStoreDTO {
    private String name;

    private String description;

    private String phone;

    private String address;

    private Store.Status status;

    private String openingHours;

    private String logoUrl;
}
