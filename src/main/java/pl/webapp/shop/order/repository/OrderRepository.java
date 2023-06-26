package pl.webapp.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.order.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserUuidOrderByIdDesc(String userUuid);

    Optional<Order> findByOrderHash(String orderHash);
}
