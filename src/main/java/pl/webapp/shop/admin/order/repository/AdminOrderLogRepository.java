package pl.webapp.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.order.model.AdminOrderLog;

@Repository
public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long> {
}
