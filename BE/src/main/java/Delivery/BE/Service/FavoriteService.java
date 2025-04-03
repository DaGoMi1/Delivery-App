package Delivery.BE.Service;

import Delivery.BE.DTO.CreateFavoriteDTO;
import Delivery.BE.DTO.ResponseFavoriteDTO;
import Delivery.BE.DTO.ResponseRatingDTO;
import Delivery.BE.Domain.*;
import Delivery.BE.Exception.AlreadyRegisteredException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MemberService memberService;
    private final StoreService storeService;
    private final RedisRatingService redisRatingService;

    @Transactional
    public void createFavorite(CreateFavoriteDTO createFavoriteDTO) {
        Member member = memberService.getMemberInfo();
        Store store = storeService.findStoreById(createFavoriteDTO.getStoreId());

        FavoriteId favoriteId = new FavoriteId(store, member);

        if (checkFavoriteToStore(favoriteId)) throw new AlreadyRegisteredException("이미 찜한 가게 입니다.");

        Favorite favorite = Favorite.builder()
                .favoriteId(favoriteId)
                .build();

        favoriteRepository.save(favorite);
    }

    public List<ResponseFavoriteDTO> getFavorites() {
        Member member = memberService.getMemberInfo();
        Set<Store> favorites = member.getFavoriteStores();

        return favorites.stream()
                .map(favorite -> {
                    Long storeId = favorite.getId();
                    ResponseRatingDTO responseRatingDTO = redisRatingService.getRating(storeId); // 평점 DTO
                    return new ResponseFavoriteDTO(favorite, responseRatingDTO); // 찜 반환 DTO에 평점 DTO 추가
                })
                .collect(Collectors.toList());
    }

    public void deleteFavorite(Long storeId) {
        Member member = memberService.getMemberInfo();
        Store store = storeService.findStoreById(storeId);

        FavoriteId favoriteId = new FavoriteId(store, member);

        if (!checkFavoriteToStore(favoriteId)) throw new NotFoundException("찜 하지 않은 가게 입니다.");

        Favorite favorite = findFavoriteById(favoriteId);
        favoriteRepository.delete(favorite);
    }

    private boolean checkFavoriteToStore(FavoriteId favoriteId) {
        return favoriteRepository.existsByFavoriteId(favoriteId);
    }

    private Favorite findFavoriteById(FavoriteId favoriteId) {
        return favoriteRepository.findByFavoriteId(favoriteId)
                .orElseThrow(() -> new NotFoundException("찜을 찾을 수 없습니다. ID: " + favoriteId));
    }
}
