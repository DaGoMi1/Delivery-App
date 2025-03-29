package Delivery.BE.Repository;

import Delivery.BE.Domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.order.store.id = :storeId")
    List<Review> findAllByStoreId(@Param("storeId") Long storeId);

    @Query("SELECT COUNT(*) FROM Review r WHERE r.order.store.id = :storeId")
    long countByStoreId(Long storeId);
}
