package delivery_App.BE.DTO;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
}
