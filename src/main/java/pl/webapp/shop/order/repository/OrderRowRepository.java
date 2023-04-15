package pl.webapp.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.order.model.OrderRow;

@Repository
public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
