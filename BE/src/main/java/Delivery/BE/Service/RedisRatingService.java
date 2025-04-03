package Delivery.BE.Service;

import Delivery.BE.DTO.ResponseRatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisRatingService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void updateRating(Long storeId, int ratingChange, int countChange) {
        if (redisTemplate.opsForValue().get("store:rating_sum:" + storeId) == null)
            redisTemplate.opsForValue().set("store:rating_sum:" + storeId, 0);

        if (redisTemplate.opsForValue().get("store:review_count:" + storeId) == null)
            redisTemplate.opsForValue().set("store:review_count:" + storeId, 0);


        redisTemplate.opsForValue().increment("store:rating_sum:" + storeId, ratingChange);
        redisTemplate.opsForValue().increment("store:review_count:" + storeId, countChange);
    }

    public ResponseRatingDTO getRating(Long storeId) {
        Integer ratingSum = getRatingSum(storeId);
        Integer reviewCount = getReviewCount(storeId);

        // null 방지 (Redis에 데이터가 없을 경우 기본값 설정)
        ratingSum = (ratingSum != null) ? ratingSum : 0;
        reviewCount = (reviewCount != null) ? reviewCount : 0;

        double rating = (reviewCount == 0) ? 0.0 : Math.round((ratingSum / (reviewCount * 1.0)) * 10) / 10.0;
        return new ResponseRatingDTO(rating, reviewCount);
    }

    private Integer getRatingSum(Long storeId) {
        return (Integer) redisTemplate.opsForValue().get("store:rating_sum:" + storeId); // Redis에서 가게의 점수 누적합
    }

    private Integer getReviewCount(Long storeId) {
        return (Integer) redisTemplate.opsForValue().get("store:review_count:" + storeId); // Redis에서 가게의 리뷰 개수
    }
}
