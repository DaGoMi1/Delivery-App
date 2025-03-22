package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "store_category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreCategory {
    @EmbeddedId
    private StoreCategoryId storeCategoryId;
}
