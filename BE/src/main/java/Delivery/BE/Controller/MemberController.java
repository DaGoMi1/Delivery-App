package Delivery.BE.Controller;

import Delivery.BE.DTO.FindMemberDTO;
import Delivery.BE.DTO.RegisterDTO;
import Delivery.BE.Service.FindMemberService;
import Delivery.BE.Service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final RegisterService registerService;
    private final FindMemberService findMemberService;

    @PostMapping("/register") // 회원가입
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        registerService.register(registerDTO); // 회원가입 서비스 로직 호출
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/find-id") // ID 찾기
    public ResponseEntity<?> findID(@RequestBody FindMemberDTO findMemberDTO) {
        findMemberService.findUserId(findMemberDTO);
        return ResponseEntity.ok("이메일 전송 완료");
    }

    @PostMapping("/find-password") // 비밀번호 찾기
    public ResponseEntity<?> findPassword(@RequestBody FindMemberDTO findMemberDTO) {
        findMemberService.findPassword(findMemberDTO);
        return ResponseEntity.ok("이메일 전송 완료");
    }
}
