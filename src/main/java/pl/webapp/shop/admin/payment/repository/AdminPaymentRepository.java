package pl.webapp.shop.admin.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.common.model.AdminPayment;

@Repository
public interface AdminPaymentRepository extends JpaRepository<AdminPayment, Long> {
}
