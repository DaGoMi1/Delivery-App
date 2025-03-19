package Delivery.BE.DTO;

import lombok.Data;

@Data
public class ResponseJwtDTO {
    private String accessToken;
    private String refreshToken;
}
