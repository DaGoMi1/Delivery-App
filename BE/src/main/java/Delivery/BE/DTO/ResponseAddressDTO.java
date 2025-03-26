package Delivery.BE.DTO;

import Delivery.BE.Domain.MemberAddress;
import lombok.Data;

@Data
public class ResponseAddressDTO {
    private Long addressId;
    private String address;
    private String alias;
    private String detailAddress;
    private boolean isMain;

    public ResponseAddressDTO(MemberAddress address) {
        this.addressId = address.getId();
        this.address = address.getAddress();
        this.alias = address.getAlias();
        this.detailAddress = address.getDetailAddress();
        this.isMain = address.isMain();
    }
}
