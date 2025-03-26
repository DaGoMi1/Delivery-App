package Delivery.BE.DTO;

import Delivery.BE.Domain.Menu;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ResponseMenuDTO {
    private Long menuId;
    private Long storeId;
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private boolean isAvailable;
    private Set<ResponseCategoryDTO> categories;

    public ResponseMenuDTO(Menu menu) {
        this.menuId = menu.getId();
        this.storeId = menu.getStore().getId();
        this.name = menu.getName();
        this.description = menu.getDescription();
        this.price = menu.getPrice();
        this.imageUrl = menu.getImageUrl();
        this.isAvailable = menu.isAvailable();
        this.categories = menu.getCategories().stream().map(ResponseCategoryDTO::new).collect(Collectors.toSet());
    }
}
