package Delivery.BE.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "favorite")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {
    @EmbeddedId
    private FavoriteId favoriteId;
}
