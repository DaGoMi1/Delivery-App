package Delivery.BE.DTO;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String address;
    private String alias;
    private String detailAddress;
    private boolean isMain;

    public AddressDTO(Long id, String address, String alias, String detailAddress, boolean isMain) {
        this.id = id;
        this.address = address;
        this.alias = alias;
        this.detailAddress = detailAddress;
        this.isMain = isMain;
    }
}
