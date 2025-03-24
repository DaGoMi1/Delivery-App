package Delivery.BE.DTO;

import lombok.Data;

@Data
public class UpdateOptionDTO {
    private String name; // 옵션 상세 이름 ex) 맵기 1단계, 단품, 大자

    private Integer price; // 옵션의 가격
}
