package Delivery.BE.Controller;

import Delivery.BE.DTO.CreateReviewDTO;
import Delivery.BE.DTO.ResponseReviewDTO;
import Delivery.BE.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<?> createReview(@Valid @RequestBody CreateReviewDTO createReviewDTO) {
        reviewService.createReview(createReviewDTO);
        return ResponseEntity.ok("리뷰 생성 완료");
    }

    @GetMapping("")
    public ResponseEntity<?> getAllMyReviews() { // 사용자가 쓴 모든 리뷰 조회
        List<ResponseReviewDTO> reviewDTOList = reviewService.getAllMyReviews();
        return ResponseEntity.ok(reviewDTOList);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<?> getAllStoreReview(@PathVariable Long id) { // 특정한 가게의 모든 리뷰 조회
        List<ResponseReviewDTO> reviewDTOList = reviewService.getAllStoreReviews(id);
        return ResponseEntity.ok(reviewDTOList);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("리뷰 삭제 완료");
    }
}
