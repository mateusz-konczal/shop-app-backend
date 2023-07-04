package pl.webapp.shop.admin.shipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.admin.common.model.AdminShipment;

@Repository
public interface AdminShipmentRepository extends JpaRepository<AdminShipment, Long> {

    @Query("UPDATE AdminShipment s SET s.enabled = TRUE WHERE s.id = :id")
    @Modifying
    void enableShipmentById(Long id);

    @Query("UPDATE AdminShipment s SET s.enabled = FALSE WHERE s.id = :id")
    @Modifying
    void disableShipmentById(Long id);
}
