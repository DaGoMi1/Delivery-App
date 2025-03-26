package Delivery.BE.DTO;

import Delivery.BE.Domain.Category;
import lombok.Data;

@Data
public class ResponseCategoryDTO {
    private Long categoryId;
    private String name;

    public ResponseCategoryDTO(Category category) {
        this.categoryId = category.getId();
        this.name = category.getName();
    }
}
