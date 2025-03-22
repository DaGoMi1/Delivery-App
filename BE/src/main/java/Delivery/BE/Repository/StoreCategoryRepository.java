package Delivery.BE.Repository;

import Delivery.BE.Domain.StoreCategory;
import Delivery.BE.Domain.StoreCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {
    boolean existsByStoreCategoryId(StoreCategoryId storeCategoryId);
}
