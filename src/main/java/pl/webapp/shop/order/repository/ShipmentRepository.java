package pl.webapp.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.order.model.Shipment;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findAllByEnabledIsTrue();
}
