package Delivery.BE.DTO;

import Delivery.BE.Domain.Store;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ResponseStoreDTO {
    private Long storeId;
    private String name;
    private Long memberId;
    private String description;
    private String phone;
    private String address;
    private Store.Status status;
    private String openingHours;
    private String logoUrl;
    private Set<ResponseCategoryDTO> categories;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    private ResponseRatingDTO ratingDTO;

    private int favoriteCount;

    public ResponseStoreDTO(Store store, ResponseRatingDTO ratingDTO, int favoriteCount) {
        this.storeId = store.getId();
        this.name = store.getName();
        this.memberId = store.getMember().getId();
        this.description = store.getDescription();
        this.phone = store.getPhone();
        this.address = store.getAddress();
        this.status = store.getStatus();
        this.openingHours = store.getOpeningHours();
        this.logoUrl = store.getLogoUrl();
        this.categories = store.getCategories().stream().map(ResponseCategoryDTO::new).collect(Collectors.toSet());

        this.createdAt = store.getCreatedAt();
        this.updatedAt = store.getUpdatedAt();

        this.ratingDTO = ratingDTO;

        this.favoriteCount = favoriteCount;
    }
}
