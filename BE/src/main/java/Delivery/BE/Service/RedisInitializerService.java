package Delivery.BE.Service;

import Delivery.BE.Domain.Store;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisInitializerService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final StoreService storeService;

    @EventListener(ApplicationReadyEvent.class) // Application 시작 후 실행되도록 변경
    @PostConstruct // 서버가 실행될 때 자동 실행
    public void loadRatingsIntoRedis() { // 별점 Redis에 저장
        List<Store> stores = storeService.findAllStores();
        for (Store store : stores) {
            Long storeId = store.getId();
            int ratingSum = storeService.getRatingSumByStoreId(storeId);
            int reviewCount = storeService.getReviewCountByStoreId(storeId);

            redisTemplate.opsForValue().set("store:rating_sum:" + storeId, ratingSum);
            redisTemplate.opsForValue().set("store:review_count:" + storeId, reviewCount);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    @PostConstruct
    public void loadFavoritesIntoRedis() {
        List<Store> stores = storeService.findAllStores();
        for (Store store : stores) {
            Long storeId = store.getId();
            int favoriteCount = storeService.countFavoriteStore(storeId);

            redisTemplate.opsForValue().set("store:favorite_count:" + storeId, favoriteCount);
        }
    }
}
