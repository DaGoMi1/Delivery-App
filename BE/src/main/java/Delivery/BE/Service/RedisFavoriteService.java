package Delivery.BE.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisFavoriteService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void updateFavorite(Long storeId, boolean isFavorite) { // 찜 생성 or 추가 시 Redis 업데이트
        if (isFavorite) {
            redisTemplate.opsForValue().increment("store:favorite_count:" + storeId, 1);
        } else {
            redisTemplate.opsForValue().decrement("store:favorite_count:" + storeId, 1);
        }
    }

    public int getFavoriteCount(Long storeId) { // Redis에 저장된 찜 개수 반환
        Integer count = (Integer) redisTemplate.opsForValue().get("store:favorite_count:" + storeId);
        return count == null ? 0 : count;
    }
}
