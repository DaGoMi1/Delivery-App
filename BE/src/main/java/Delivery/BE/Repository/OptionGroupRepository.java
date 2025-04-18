package Delivery.BE.Repository;

import Delivery.BE.Domain.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {

}
