package Delivery.BE.DTO;

import lombok.Data;

@Data
public class ResponseRatingDTO {
    private Double rating;
    private int reviewCount;

    public ResponseRatingDTO(Double rating, int reviewCount) {
        this.rating = rating;
        this.reviewCount = reviewCount;
    }
}
