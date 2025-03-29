package Delivery.BE.Repository;

import Delivery.BE.Domain.Favorite;
import Delivery.BE.Domain.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    Optional<Favorite> findByFavoriteId(FavoriteId favoriteId);
    boolean existsByFavoriteId(FavoriteId favoriteId);
}
