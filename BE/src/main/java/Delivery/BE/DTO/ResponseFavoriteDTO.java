package Delivery.BE.DTO;

import Delivery.BE.Domain.Store;
import lombok.Data;

@Data
public class ResponseFavoriteDTO {
    private Long storeId;

    private String storeName;
    private Double storeRating;
    private String storeImage;
    private String storeDescription;

    public ResponseFavoriteDTO(Store store) {
        this.storeId = store.getId();

        this.storeName = store.getName();
        this.storeRating = store.getRating();
        this.storeDescription = store.getDescription();
        this.storeImage = store.getLogoUrl();
    }
}
