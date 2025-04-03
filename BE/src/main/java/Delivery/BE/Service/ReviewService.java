package Delivery.BE.Service;

import Delivery.BE.DTO.CreateReviewDTO;
import Delivery.BE.DTO.ResponseReviewDTO;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Order;
import Delivery.BE.Domain.Review;
import Delivery.BE.Exception.ForbiddenException;
import Delivery.BE.Exception.NotFoundException;
import Delivery.BE.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderService orderService;
    private final MemberService memberService;
    private final RedisRatingService redisRatingService;

    @Transactional
    public void createReview(CreateReviewDTO createReviewDTO) {
        Member member = memberService.getMemberInfo();
        Order order = orderService.findOrderById(createReviewDTO.getOrderId());
        orderService.checkOrderOwner(order);

        Review review = Review.builder()
                .member(member)
                .order(order)
                .rating(createReviewDTO.getRating())
                .comment(createReviewDTO.getComment())
                .build();

        reviewRepository.save(review);

        // Redis에 리뷰 점수와 리뷰 개수 1 증가 시키기 (가게 조회시 DB 접근 X, Redis에서 빠른 조회)
        Long storeId = order.getStore().getId();
        redisRatingService.updateRating(storeId, createReviewDTO.getRating(), 1);
    }

    public List<ResponseReviewDTO> getAllMyReviews() {
        Member member = memberService.getMemberInfo();
        List<Review> reviews = member.getReviews();

        return reviews.stream().map(ResponseReviewDTO::new).toList();
    }

    public List<ResponseReviewDTO> getAllStoreReviews(Long storeId) {
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);

        return reviews.stream().map(ResponseReviewDTO::new).toList();
    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = findReviewById(id);
        checkReviewOwner(review);
        reviewRepository.delete(review);

        // 마찬가지로 Redis에 삭제된 리뷰에 대한 합과 개수 감소 시키기
        Long storeId = review.getOrder().getStore().getId();
        redisRatingService.updateRating(storeId, -review.getRating(), -1);
    }

    public Review findReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("리뷰를 찾을 수 없습니다. ID: " + id));
    }

    public void checkReviewOwner(Review review) { // 요청한 리뷰에 대한 정보가 소유자가 맞는지 확인
        Member member = memberService.getMemberInfo();
        Member reviewOwner = review.getMember();

        if (!Objects.equals(reviewOwner, member) && member.getRole() != Member.Role.ADMIN)
            throw new ForbiddenException("해당 리뷰의 소유자가 아닙니다.");
    }
}
