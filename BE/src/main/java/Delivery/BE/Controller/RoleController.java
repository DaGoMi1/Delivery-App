package Delivery.BE.Controller;

import Delivery.BE.DTO.RoleUpgradeDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Service.MemberService;
import Delivery.BE.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final MemberService memberService;

    @PatchMapping("/upgrade-to-owner") // 점주 계정 요청
    public ResponseEntity<?> upgradeToOwner(@RequestBody RoleUpgradeDTO roleUpgradeDTO) {
        Member member = memberService.getMemberInfo();
        roleService.upgradeToOwner(member, roleUpgradeDTO);
        return ResponseEntity.ok("점주의 권한으로 업데이트 완료");
    }
}
