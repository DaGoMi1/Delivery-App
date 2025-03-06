package Delivery.BE.Controller;

import Delivery.BE.DTO.FindMemberDTO;
import Delivery.BE.DTO.RegisterDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Service.MemberService;
import Delivery.BE.Service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final RegisterService registerService;
    private final MemberService memberService;

    @PostMapping("/register") // 회원가입
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        registerService.register(registerDTO); // 회원가입 서비스 로직 호출
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/find-id") // ID 찾기
    public ResponseEntity<?> findID(@RequestBody FindMemberDTO findMemberDTO) {
        memberService.findUserId(findMemberDTO);
        return ResponseEntity.ok("이메일 전송 완료");
    }

    @PostMapping("/find-password") // 비밀번호 찾기
    public ResponseEntity<?> findPassword(@RequestBody FindMemberDTO findMemberDTO) {
        memberService.findPassword(findMemberDTO);
        return ResponseEntity.ok("이메일 전송 완료");
    }

    @GetMapping("/info") // Member 정보 요청
    public ResponseEntity<?> info() {
        // SecurityContextHolder에 저장된 userId 가져오기
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findMemberByUserId(userId); // userId로 Member 찾기
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/withdraw") // Member 탈퇴 요청
    public ResponseEntity<?> withdraw() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        memberService.withdrawMember(userId);
        return ResponseEntity.ok("회원 탈퇴 완료");
    }
}
