package Delivery.BE.Service;

import Delivery.BE.DTO.CreateStoreDTO;
import Delivery.BE.DTO.ResponseRatingDTO;
import Delivery.BE.DTO.ResponseStoreDTO;
import Delivery.BE.DTO.UpdateStoreDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Store;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final MemberService memberService;
    private final RedisRatingService redisRatingService;

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
                .build();

        storeRepository.save(store);
    }

    @Transactional
    public void updateStore(Long id, UpdateStoreDTO updateStoreDTO) {
        Store store = findStoreById(id);

        checkStoreOwner(store);

        if (updateStoreDTO.getName() != null) store.setName(updateStoreDTO.getName());
        if (updateStoreDTO.getAddress() != null) store.setAddress(updateStoreDTO.getAddress());
        if (updateStoreDTO.getPhone() != null) store.setPhone(updateStoreDTO.getPhone());
        if (updateStoreDTO.getDescription() != null) store.setDescription(updateStoreDTO.getDescription());
        if (updateStoreDTO.getOpeningHours() != null) store.setOpeningHours(updateStoreDTO.getOpeningHours());
        if (updateStoreDTO.getStatus() != null) store.setStatus(updateStoreDTO.getStatus());
        if (updateStoreDTO.getLogoUrl() != null) store.setLogoUrl(updateStoreDTO.getLogoUrl());

        storeRepository.save(store);
    }

    @Transactional
    public void deleteStore(Long id) {
        Store store = findStoreById(id);
        checkStoreOwner(store);
        storeRepository.deleteById(id);
    }

    public List<ResponseStoreDTO> getStoresByCategory(Long categoryId) {
        List<Store> storeList = storeRepository.findStoresByCategoryId(categoryId);
        return storeToDTO(storeList);
    }

    public List<ResponseStoreDTO> getStoresByName(String name) {
        List<Store> storeList = storeRepository.findStoresByName(name);
        return storeToDTO(storeList);
    }

    // Redis에서 가게의 리뷰 평점 합과 리뷰 개수로 평점 계산
    private List<ResponseStoreDTO> storeToDTO(List<Store> storeList) {
        return storeList.stream()
                .map(store -> {
                    ResponseRatingDTO responseRatingDTO = redisRatingService.getRating(store.getId()); // 평점 DTO
                    return new ResponseStoreDTO(store, responseRatingDTO); // 가게 반환 DTO에 평점 DTO 추가
                })
                .collect(Collectors.toList());
    }

    public Store findStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("가게를 찾을 수 없습니다. ID: " + id));
    }

    public void checkStoreOwner(Store store) { // 요청한 가게에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();
        Member storeOwner = store.getMember();

        if (!Objects.equals(storeOwner, member) && member.getRole() != Member.Role.ADMIN)
            throw new ForbiddenException("해당 가게의 소유자가 아닙니다.");
    }

    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    public int getRatingSumByStoreId(Long storeId) {
        return storeRepository.getRatingSumByStoreId(storeId);
    }

    public int getReviewCountByStoreId(Long storeId) {
        return storeRepository.getReviewCountByStoreId(storeId);
    }
}