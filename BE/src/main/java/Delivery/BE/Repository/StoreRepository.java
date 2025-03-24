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
}
