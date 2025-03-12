package Delivery.BE.Controller;

import Delivery.BE.DTO.AddressDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Service.AddressService;
import Delivery.BE.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final MemberService memberService;
    private final AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody AddressDTO addressDTO) {
        Member member = memberService.getMemberInfo();
        addressService.addAddress(addressDTO, member);
        return ResponseEntity.ok("Member 주소 추가 완료");
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAddressList() {
        Member member = memberService.getMemberInfo();
        return ResponseEntity.ok(addressService.getAddressList(member));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDTO addressDTO) {
        addressService.updateAddress(addressDTO);
        return ResponseEntity.ok("상세 주소 수정 완료");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAddress(@RequestBody AddressDTO addressDTO) {
        addressService.deleteAddress(addressDTO);
        return ResponseEntity.ok("Member의 주소 삭제 완료");
    }
}
