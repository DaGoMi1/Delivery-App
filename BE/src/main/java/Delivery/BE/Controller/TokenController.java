package Delivery.BE.Controller;

import Delivery.BE.DTO.AccessTokenResponseDTO;
import Delivery.BE.DTO.JwtResponseDTO;
import Delivery.BE.DTO.LoginDTO;
import Delivery.BE.Service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        JwtResponseDTO jwtResponseDTO = tokenService.login(loginDTO); // 로그인 서비스 로직 호출
        return ResponseEntity.ok(jwtResponseDTO); // Jwt 반환
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        // Refresh 토큰으로 새로운 Access 토큰 발급하는 로직 호출
        AccessTokenResponseDTO accessTokenResponseDTO = tokenService.reissueAccessToken(refreshToken);
        return ResponseEntity.ok(accessTokenResponseDTO);
    }
}
