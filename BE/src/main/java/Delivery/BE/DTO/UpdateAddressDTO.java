package Delivery.BE.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateAddressDTO {
    @Length(min = 1, max = 255)
    private String address;

    @Length(min = 1, max = 20)
    private String alias;

    @Length(min = 1, max = 255)
    private String detailAddress;
}
