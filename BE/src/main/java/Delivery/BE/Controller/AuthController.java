package Delivery.BE.Controller;

import Delivery.BE.DTO.ResponseAccessTokenDTO;
import Delivery.BE.DTO.ResponseJwtDTO;
import Delivery.BE.DTO.LoginDTO;
import Delivery.BE.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        ResponseJwtDTO responseJwtDTO = authService.login(loginDTO); // 로그인 서비스 로직 호출
        return ResponseEntity.ok(responseJwtDTO); // Jwt 반환
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        // Refresh 토큰으로 새로운 Access 토큰 발급하는 로직 호출
        ResponseAccessTokenDTO responseAccessTokenDTO = authService.reissueAccessToken(refreshToken);
        return ResponseEntity.ok(responseAccessTokenDTO);
    }
}
