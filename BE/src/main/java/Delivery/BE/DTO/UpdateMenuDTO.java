package Delivery.BE.DTO;

import lombok.Data;

@Data
public class UpdateMenuDTO {
    private String name;                // 메뉴 이름

    private String description;         // 메뉴 설명

    private Integer price;               // 메뉴 가격

    private String imageUrl;            // 메뉴 이미지 경로

    private Boolean isAvailable;     // 메뉴 사용 가능 여부
}
