package Delivery.BE.DTO;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
}
