package Delivery.BE.Service;

import Delivery.BE.DTO.CreateStoreDTO;
import Delivery.BE.DTO.UpdateStoreDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Store;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberService memberService;

    @Transactional
    public void createStore(CreateStoreDTO createStoreDTO, Member member) {

        Store store = Store.builder()
                .member(member)
                .name(createStoreDTO.getName())
                .address(createStoreDTO.getAddress())
                .phone(createStoreDTO.getPhone())
                .description(createStoreDTO.getDescription())
                .openingHours(createStoreDTO.getOpeningHours())
                .status(Store.Status.CLOSED)
                .rating(0.0)
                .build();

        storeRepository.save(store);
    }

    @Transactional
    public void updateStore(Long id, UpdateStoreDTO updateStoreDTO) {
        Store store = findStoreById(id);

        checkStoreOwner(store);

        if(updateStoreDTO.getName() != null) store.setName(updateStoreDTO.getName());
        if(updateStoreDTO.getAddress() != null) store.setAddress(updateStoreDTO.getAddress());
        if(updateStoreDTO.getPhone() != null) store.setPhone(updateStoreDTO.getPhone());
        if(updateStoreDTO.getDescription() != null) store.setDescription(updateStoreDTO.getDescription());
        if(updateStoreDTO.getOpeningHours() != null) store.setOpeningHours(updateStoreDTO.getOpeningHours());
        if(updateStoreDTO.getStatus() != null) store.setStatus(updateStoreDTO.getStatus());
        if(updateStoreDTO.getLogoUrl() != null) store.setLogoUrl(updateStoreDTO.getLogoUrl());

        storeRepository.save(store);
    }

    @Transactional
    public void deleteStore(Long id) {
        Store store = findStoreById(id);
        checkStoreOwner(store);
        storeRepository.deleteById(id);
    }

    public Store findStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("가게를 찾을 수 없습니다. ID: " + id));
    }

    public void checkStoreOwner(Store store) { // 요청한 가게에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();
        Member storeOwner = store.getMember();

        if (!Objects.equals(storeOwner, member)) throw new ForbiddenException("해당 가게의 소유자가 아닙니다.");
    }
}