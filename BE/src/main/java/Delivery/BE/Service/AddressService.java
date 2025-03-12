package Delivery.BE.Service;

import Delivery.BE.DTO.AddressDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.MemberAddress;
import Delivery.BE.Exception.AddressNotFoundException;
import Delivery.BE.Exception.MissingRequiredDataException;
import Delivery.BE.Repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    @Transactional
    public void addAddress(AddressDTO addressDTO, Member member) {
        String address = addressDTO.getAddress();
        String alias = addressDTO.getAlias();
        String detailAddress = addressDTO.getDetailAddress();

        if (address == null || address.isEmpty() || alias == null || alias.isEmpty()) {
            throw new MissingRequiredDataException("필요한 데이터가 불충분합니다.");
        }

        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setAddress(address);
        memberAddress.setAlias(alias);
        memberAddress.setDetailAddress(detailAddress);
        memberAddress.setMember(member);

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
    public void updateAddress(AddressDTO addressDTO) {
        Long id = addressDTO.getId();
        String detailAddress = addressDTO.getDetailAddress();

        if (detailAddress == null || detailAddress.isEmpty()) {
            throw new MissingRequiredDataException("상세 주소를 입력하세요.");
        }

        MemberAddress memberAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("해당 ID의 주소를 찾을 수 없습니다."));

        memberAddress.setDetailAddress(detailAddress);
        addressRepository.save(memberAddress);
    }

    public List<AddressDTO> getAddressList(Member member) {
        Long memberId = member.getId();
        List<MemberAddress> memberAddresses = addressRepository.findByMemberId(memberId);

        return memberAddresses.stream()
                .map(memberAddress -> new AddressDTO(
                        memberAddress.getId(),
                        memberAddress.getAddress(),
                        memberAddress.getAlias(),
                        memberAddress.getDetailAddress(),
                        memberAddress.isMain()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddress(AddressDTO addressDTO) {
        Long id = addressDTO.getId();

        addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("해당 ID의 주소를 찾을 수 없습니다."));

        addressRepository.deleteById(id);
    }

    @Transactional
    public void setMainAddress(AddressDTO addressDTO, Member member) {
        Long id = addressDTO.getId();

        // Member의 모든 주소 가져오기
        List<MemberAddress> memberAddresses = addressRepository.findByMemberId(member.getId());

        // 가져온 주소 리스트의 main 값을 모두 false 로 설정
        memberAddresses.forEach(address -> address.setMain(false));

        // 요청한 id의 주소만 main 값을 true 로 설정
        MemberAddress mainAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("해당 ID의 주소를 찾을 수 없습니다."));
        mainAddress.setMain(true);

        // 저장
        addressRepository.saveAll(memberAddresses);
    }
}
