package Delivery.BE.Service;

import Delivery.BE.DTO.CreateStoreDTO;
import Delivery.BE.DTO.ResponseRatingDTO;
import Delivery.BE.DTO.ResponseStoreDTO;
import Delivery.BE.DTO.UpdateStoreDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Store;
import Delivery.BE.Enum.SortType;
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
    private final RedisFavoriteService redisFavoriteService;
    private final SortService sortService;

    @Transactional
    public void createStore(CreateStoreDTO createStoreDTO, Member member) { // 가게 생성

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
    public void updateStore(Long id, UpdateStoreDTO updateStoreDTO) { // 가게 수정
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
    public void deleteStore(Long id) { // 가게 삭제
        Store store = findStoreById(id);
        checkStoreOwner(store);
        storeRepository.deleteById(id);
    }

    public List<ResponseStoreDTO> getStoresByCategory(Long categoryId, SortType type) { // 카테고리에 맞는 가게 조회
        List<Store> storeList = storeRepository.findStoresByCategoryId(categoryId);
        List<ResponseStoreDTO> storeDTOList = storeToDTO(storeList);
        return sortService.sort(storeDTOList, type);
    }

    public List<ResponseStoreDTO> getStoresByName(String name, SortType type) { // 이름으로 가게 조회
        List<Store> storeList = storeRepository.findStoresByName(name);
        List<ResponseStoreDTO> storeDTOList = storeToDTO(storeList);
        return sortService.sort(storeDTOList, type);
    }

    public int countFavoriteStore(Long storeId) { // 가게 찜 개수 구하기
        return storeRepository.getFavoriteCountByStoreId(storeId);
    }

    // DTO로 변환
    private List<ResponseStoreDTO> storeToDTO(List<Store> storeList) {
        return storeList.stream()
                .map(store -> {
                    ResponseRatingDTO responseRatingDTO = redisRatingService.getRating(store.getId()); // 평점 DTO
                    int favoriteCount = redisFavoriteService.getFavoriteCount(store.getId()); // 찜 개수
                    return new ResponseStoreDTO(store, responseRatingDTO, favoriteCount); // 가게 반환 DTO에 평점 DTO 추가
                })
                .collect(Collectors.toList());
    }

    public Store findStoreById(Long id) { // ID로 가게 찾기
        return storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("가게를 찾을 수 없습니다. ID: " + id));
    }

    public void checkStoreOwner(Store store) { // 요청한 가게에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();
        Member storeOwner = store.getMember();

        if (!Objects.equals(storeOwner, member) && member.getRole() != Member.Role.ADMIN)
            throw new ForbiddenException("해당 가게의 소유자가 아닙니다.");
    }

    public List<Store> findAllStores() { // 모든 가게 조회
        return storeRepository.findAll();
    }

    public int getRatingSumByStoreId(Long storeId) { // 특정 가게의 별점 누적합
        return storeRepository.getRatingSumByStoreId(storeId);
    }

    public int getReviewCountByStoreId(Long storeId) { // 특정 가게의 리뷰 개수
        return storeRepository.getReviewCountByStoreId(storeId);
    }
}