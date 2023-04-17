package pl.webapp.shop.admin.shipment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.shipment.model.AdminShipment;
import pl.webapp.shop.admin.shipment.repository.AdminShipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminShipmentService {

    private final AdminShipmentRepository shipmentRepository;

    public List<AdminShipment> getShipments() {
        return shipmentRepository.findAll();
    }

    public AdminShipment getShipment(Long id) {
        return shipmentRepository.findById(id).orElseThrow();
    }

    public AdminShipment createShipment(AdminShipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public AdminShipment updateShipment(AdminShipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }
}
