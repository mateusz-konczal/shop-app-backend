package pl.webapp.shop.admin.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.common.model.AdminPayment;

@Repository
public interface AdminPaymentRepository extends JpaRepository<AdminPayment, Long> {

    @Query("UPDATE AdminPayment p SET p.enabled = TRUE WHERE p.id = :id")
    @Modifying
    void enablePaymentById(Long id);

    @Query("UPDATE AdminPayment p SET p.enabled = FALSE WHERE p.id = :id")
    @Modifying
    void disablePaymentById(Long id);
}
