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

    private int favoriteCount;

    public ResponseFavoriteDTO(Store store, ResponseRatingDTO ratingDTO, int favoriteCount) {
        this.storeId = store.getId();

        this.storeName = store.getName();
        this.storeDescription = store.getDescription();
        this.storeImage = store.getLogoUrl();

        this.ratingDTO = ratingDTO;

        this.favoriteCount = favoriteCount;
    }
}
