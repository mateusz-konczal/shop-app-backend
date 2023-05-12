package pl.webapp.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.order.model.AdminOrder;
import pl.webapp.shop.common.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {

    List<AdminOrder> findAllByPlaceDateIsBetweenAndOrderStatus(
            LocalDateTime from,
            LocalDateTime to,
            OrderStatus orderStatus);
}
