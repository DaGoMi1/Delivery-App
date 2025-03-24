package Delivery.BE.Repository;

import Delivery.BE.Domain.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<MemberAddress, Long> {
    List<MemberAddress> findByMemberId(Long memberId);
}
