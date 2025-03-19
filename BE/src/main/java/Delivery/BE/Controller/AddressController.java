package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateAddressDTO;
import Delivery.BE.DTO.UpdateAddressDTO;
import Delivery.BE.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("")
    public ResponseEntity<?> addAddress(@Valid @RequestBody CreateAddressDTO createAddressDTO) {
        addressService.addAddress(createAddressDTO);
        return ResponseEntity.ok("Member 주소 추가 완료");
    }

    @GetMapping("")
    public ResponseEntity<?> getAddressList() {
        return ResponseEntity.ok(addressService.getAddressList());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @Valid @RequestBody UpdateAddressDTO updateAddressDTO) {
        addressService.updateAddress(id, updateAddressDTO);
        return ResponseEntity.ok("상세 주소 수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Member의 주소 삭제 완료");
    }

    @PatchMapping("/main/{id}")
    public ResponseEntity<?> setMain(@PathVariable Long id) {
        addressService.setMainAddress(id);
        return ResponseEntity.ok("대표 주소 설정 완료");
    }
}
