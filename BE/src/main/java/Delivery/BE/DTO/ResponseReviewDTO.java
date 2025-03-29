package Delivery.BE.DTO;

import Delivery.BE.Domain.Review;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseReviewDTO {
    private Long reviewId;
    private Long orderId;

    private int rating;
    private String comment;

    private Timestamp createDate;

    public ResponseReviewDTO(Review review) {
        this.reviewId = review.getId();
        this.orderId = review.getOrder().getId();

        this.rating = review.getRating();
        this.comment = review.getComment();

        this.createDate = review.getCreatedAt();
    }
}
