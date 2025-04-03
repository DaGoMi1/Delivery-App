package Delivery.BE.DTO;

import Delivery.BE.Domain.Store;
import lombok.Data;

@Data
public class ResponseFavoriteDTO {
    private Long storeId;

    private String storeName;
    private String storeImage;
    private String storeDescription;

    private ResponseRatingDTO ratingDTO;

    public ResponseFavoriteDTO(Store store, ResponseRatingDTO ratingDTO) {
        this.storeId = store.getId();

        this.storeName = store.getName();
        this.storeDescription = store.getDescription();
        this.storeImage = store.getLogoUrl();

        this.ratingDTO = ratingDTO;
    }
}
