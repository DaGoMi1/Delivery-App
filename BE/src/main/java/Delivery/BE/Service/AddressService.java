package Delivery.BE.Service;

import Delivery.BE.DTO.AddressDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.MemberAddress;
import Delivery.BE.Exception.AddressNotFoundException;
import Delivery.BE.Exception.MissingRequiredDataException;
import Delivery.BE.Repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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
                .map(memberAddress -> new AddressDTO(memberAddress.getId(), memberAddress.getAddress(), memberAddress.getAlias(), memberAddress.getDetailAddress()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAddress(AddressDTO addressDTO) {
        Long id = addressDTO.getId();

        addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("해당 ID의 주소를 찾을 수 없습니다."));

        addressRepository.deleteById(id);
    }
}
