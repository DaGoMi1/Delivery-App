package Delivery.BE.Service;

import Delivery.BE.DTO.CreateStoreDTO;
import Delivery.BE.DTO.UpdateStoreDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Store;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

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
    public void updateStore(UpdateStoreDTO updateStoreDTO) {
        Long id = updateStoreDTO.getId();
        Store store = findStoreById(id);

        if(updateStoreDTO.getName() != null) store.setName(updateStoreDTO.getName());
        if(updateStoreDTO.getAddress() != null) store.setAddress(updateStoreDTO.getAddress());
        if(updateStoreDTO.getPhone() != null) store.setPhone(updateStoreDTO.getPhone());
        if(updateStoreDTO.getDescription() != null) store.setDescription(updateStoreDTO.getDescription());
        if(updateStoreDTO.getOpeningHours() != null) store.setOpeningHours(updateStoreDTO.getOpeningHours());
        if(updateStoreDTO.getStatus() != null) store.setStatus(updateStoreDTO.getStatus());
        if(updateStoreDTO.getLogoUrl() != null) store.setLogoUrl(updateStoreDTO.getLogoUrl());

        storeRepository.save(store);
    }

    private Store findStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("가게를 찾을 수 없습니다. ID: " + id));
    }
}