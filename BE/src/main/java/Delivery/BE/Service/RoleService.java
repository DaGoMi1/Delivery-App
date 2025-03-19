package Delivery.BE.Service;

import Delivery.BE.DTO.RoleUpgradeDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Exception.InvalidOwnerCodeException;
import Delivery.BE.Exception.UnauthorizedRoleException;
import Delivery.BE.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {
    private final MemberRepository memberRepository;

    private static final String OWNER_CODE = "사업자 등록증 032";

    public void upgradeToOwner(Member member, RoleUpgradeDTO roleUpgradeDTO) {
        Member.Role role = member.getRole(); // 사용자의 권한 가져오기

        if (!role.equals(Member.Role.CUSTOMER)) { // 일반 사용자가 아니라면 (OWNER, ADMIN)
            throw new UnauthorizedRoleException("일반 사용자만 가능한 요청입니다.");
        }

        // 사업자 등록증이 인증 실패라면 (실제로는 훨씬 다양하고 복잡한 과정이겠지만...)
        if (!roleUpgradeDTO.getOwnerCode().equals(OWNER_CODE)) {
            throw new InvalidOwnerCodeException("사업자 등록증 인증에 실패하였습니다.");
        }

        // 업그레이드 처리 로직 (여기서 역할 변경)
        member.setRole(Member.Role.OWNER);
        memberRepository.save(member);
    }
}
