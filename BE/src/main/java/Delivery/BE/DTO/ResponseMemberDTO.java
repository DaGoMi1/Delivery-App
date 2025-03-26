package Delivery.BE.DTO;

import Delivery.BE.Domain.Member;
import lombok.Data;

@Data
public class ResponseMemberDTO {
    private Long memberId;
    private String name;
    private Member.Role role;

    public ResponseMemberDTO(Member member) {
        this.memberId = member.getId();
        this.name = member.getName();
        this.role = member.getRole();
    }
}
