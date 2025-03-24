package Delivery.BE.DTO;

import Delivery.BE.Domain.Category;
import Delivery.BE.Domain.Member;
import Delivery.BE.Domain.Store;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ResponseStoreDTO {
    private Long id;

    private String name;

    private Long memberId;

    private String description;

    private String phone;

    private String address;

    private Store.Status status;

    private String openingHours;

    private String logoUrl;

    private Double rating;

    private Set<String> categories;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public ResponseStoreDTO(Long id, String name, Member member, String description, String phone, String address
    , Store.Status status, String openingHours, String logoUrl, Double rating, Set<Category> categories
    , Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.memberId = member.getId();
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.openingHours = openingHours;
        this.logoUrl = logoUrl;
        this.rating = rating;
        this.categories = categories.stream().map(Category::getName).collect(Collectors.toSet());
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
