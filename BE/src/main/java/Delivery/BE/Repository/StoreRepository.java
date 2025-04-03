package Delivery.BE.Repository;

import Delivery.BE.Domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT s FROM Store s JOIN s.categories c WHERE c.id = :categoryId")
    List<Store> findStoresByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT s FROM Store s WHERE s.name LIKE %:name%")
    List<Store> findStoresByName(@Param("name") String name);

    @Query("SELECT COALESCE(SUM(r.rating), 0) FROM Review r WHERE r.order.store.id = :storeId")
    int getRatingSumByStoreId(@Param("storeId") Long storeId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.order.store.id = :storeId")
    int getReviewCountByStoreId(@Param("storeId") Long storeId);
}
