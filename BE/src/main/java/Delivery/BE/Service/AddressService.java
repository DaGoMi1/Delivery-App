package Delivery.BE.Service;

import Delivery.BE.DTO.CreateAddressDTO;
import Delivery.BE.DTO.ResponseAddressDTO;
import Delivery.BE.DTO.UpdateAddressDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.MemberAddress;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final MemberService memberService;

    @Transactional
    public void addAddress(CreateAddressDTO createAddressDTO) {
        Member member = memberService.getMemberInfo();

        MemberAddress memberAddress = MemberAddress.builder()
                .address(createAddressDTO.getAddress())
                .alias(createAddressDTO.getAlias())
                .detailAddress(createAddressDTO.getDetailAddress())
                .member(member)
                .build();

        // 처음 생성하는 주소라면 대표 주소로 설정 (isMain = true)
        // Member의 모든 주소 리스트 가져오기
        List<MemberAddress> existingAddresses = addressRepository.findByMemberId(member.getId());
        // setMain의 값을 리스트가 비어있는지에 대한 값으로 저장
        // 리스트가 비어있다 (처음 생성하는 주소이다) -> setMain(true), 대표 주소로 설정
        // 리스트가 비어있지 않다 (처음 생성하는 주소가 아니다) -> setMain(false), 일반 주소로 설정
        memberAddress.setMain(existingAddresses.isEmpty());

        addressRepository.save(memberAddress);
    }

    @Transactional
    public void updateAddress(Long id, UpdateAddressDTO updateAddressDTO) {
        MemberAddress memberAddress = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("주소를 찾을 수 없습니다. id=" + id));

        checkAddressMember(memberAddress);

        if (updateAddressDTO.getAddress() != null) memberAddress.setAddress(updateAddressDTO.getAddress());
        if (updateAddressDTO.getAlias() != null) memberAddress.setAlias(updateAddressDTO.getAlias());
        if (updateAddressDTO.getDetailAddress() != null)
            memberAddress.setDetailAddress(updateAddressDTO.getDetailAddress());

        addressRepository.save(memberAddress);
    }

    public List<ResponseAddressDTO> getAddressList() {
        Member member = memberService.getMemberInfo();
        Long memberId = member.getId();
        List<MemberAddress> memberAddresses = addressRepository.findByMemberId(memberId);

        return memberAddresses.stream()
                .map(ResponseAddressDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddress(Long id) {
        MemberAddress memberAddress = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("주소를 찾을 수 없습니다. ID: " + id));

        checkAddressMember(memberAddress);

        addressRepository.deleteById(id);
    }

    @Transactional
    public void setMainAddress(Long id) {
        MemberAddress memberAddress = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("주소를 찾을 수 없습니다. id=" + id));

        checkAddressMember(memberAddress);

        Member member = memberService.getMemberInfo();

        // Member의 모든 주소 가져오기
        List<MemberAddress> memberAddresses = addressRepository.findByMemberId(member.getId());

        // 가져온 주소 리스트의 main 값을 모두 false 로 설정
        memberAddresses.forEach(address -> address.setMain(false));

        // 요청한 id의 주소만 main 값을 true 로 설정
        memberAddress.setMain(true);

        // 저장
        addressRepository.saveAll(memberAddresses);
    }

    private void checkAddressMember(MemberAddress memberAddress) {
        Member member = memberService.getMemberInfo();
        Member addressMember = memberAddress.getMember();

        if (!Objects.equals(addressMember, member)) throw new ForbiddenException("해당 주소의 소유자가 아닙니다.");
    }
}
