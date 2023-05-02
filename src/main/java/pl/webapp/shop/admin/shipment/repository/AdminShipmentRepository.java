package pl.webapp.shop.admin.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.common.model.AdminShipment;

@Repository
public interface AdminShipmentRepository extends JpaRepository<AdminShipment, Long> {
}
