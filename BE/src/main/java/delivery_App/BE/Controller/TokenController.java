package delivery_App.BE.Controller;

import delivery_App.BE.DTO.AccessTokenResponseDTO;
import delivery_App.BE.DTO.JwtResponseDTO;
import delivery_App.BE.DTO.LoginDTO;
import delivery_App.BE.JWT.JwtUtil;
import delivery_App.BE.Service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            JwtResponseDTO jwtResponseDTO = tokenService.login(loginDTO); // 로그인 서비스 로직 호출
            return ResponseEntity.ok(jwtResponseDTO); // Jwt 반환
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류 발생");
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        try {
            // Refresh 토큰으로 새로운 Access 토큰 발급하는 로직 호출
            AccessTokenResponseDTO accessTokenResponseDTO = tokenService.reissueAccessToken(refreshToken);

            return ResponseEntity.ok(accessTokenResponseDTO); // 새로운 Access 토큰 반환
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류 발생");
        }
    }
}
