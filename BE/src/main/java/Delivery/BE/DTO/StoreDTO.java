package Delivery.BE.DTO;

import Delivery.BE.Domain.Category;
import Delivery.BE.Domain.Store;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
public class StoreDTO {
    private Long id;

    @NotBlank(message = "이름은 빈 칸일 수 없습니다.")
    private String name;

    private Long memberId;

    private String description;

    @NotBlank(message = "전화번호는 빈 칸일 수 없습니다.")
    private String phone;

    @NotBlank(message = "주소는 빈 칸일 수 없습니다.")
    private String address;

    private Store.Status status;

    @NotBlank(message = "영업시간은 빈 칸일 수 없습니다.")
    private String openingHours;

    private String logoUrl;

    private Double rating;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Set<Category> categories = new HashSet<>();
}
