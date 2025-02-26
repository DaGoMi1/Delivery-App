package delivery_App.BE.Controller;

import delivery_App.BE.DTO.RegisterDTO;
import delivery_App.BE.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        try {
            memberService.register(registerDTO); // 회원가입 서비스 로직 호출
            return ResponseEntity.ok("회원가입 성공");
        } catch(IllegalArgumentException e) { // 미리 정해진 예외
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e) { // 예상치 못한 예외
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류 발생");
        }
    }
}
