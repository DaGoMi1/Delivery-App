package Delivery.BE.Repository;

import Delivery.BE.Domain.CartItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemOptionRepository extends JpaRepository<CartItemOption, Long> {
}
