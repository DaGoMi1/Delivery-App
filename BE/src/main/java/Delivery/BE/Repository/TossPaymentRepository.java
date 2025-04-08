package Delivery.BE.Repository;

import Delivery.BE.Domain.TossPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TossPaymentRepository extends JpaRepository<TossPayment, Long> {
}
