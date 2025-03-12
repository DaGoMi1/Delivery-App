package Delivery.BE.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class AddressDTO {
    private Long id;
    private String address;
    private String alias;
    private String detailAddress;

    public AddressDTO(Long id, String address, String alias, String detailAddress) {
        this.id = id;
        this.address = address;
        this.alias = alias;
        this.detailAddress = detailAddress;
    }
}
