package Delivery.BE.Controller;

import Delivery.BE.DTO.ChangePasswordDTO;
import Delivery.BE.DTO.FindMemberDTO;
import Delivery.BE.DTO.RegisterDTO;
import Delivery.BE.DTO.ResponseMemberDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Service.MemberService;
import Delivery.BE.Service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final RegisterService registerService;
    private final MemberService memberService;

    @PostMapping("/register") // 회원가입
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        registerService.register(registerDTO);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/id") // ID 찾기
    public ResponseEntity<?> findID(@RequestBody FindMemberDTO findMemberDTO) {
        memberService.findUserId(findMemberDTO);
        return ResponseEntity.ok("이메일 전송 완료");
    }

    @PostMapping("/password") // 비밀번호 찾기
    public ResponseEntity<?> findPassword(@RequestBody FindMemberDTO findMemberDTO) {
        memberService.findPassword(findMemberDTO);
        return ResponseEntity.ok("이메일 전송 완료");
    }

    @GetMapping("")
    public ResponseEntity<?> info() {
        Member member = memberService.getMemberInfo();
        ResponseMemberDTO responseMemberDTO = memberService.memberInfoToResponseMemberDTO(member);
        return ResponseEntity.ok(responseMemberDTO);
    }

    @DeleteMapping("") // Member 탈퇴 요청
    public ResponseEntity<?> withdraw() {
        memberService.withdrawMember();
        return ResponseEntity.ok("회원 탈퇴 완료");
    }

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        memberService.changePassword(changePasswordDTO);
        return ResponseEntity.ok("비밀번호 변경 완료");
    }
}
