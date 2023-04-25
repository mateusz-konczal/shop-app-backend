package pl.webapp.shop.admin.shipment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.shipment.controller.dto.AdminShipmentDto;
import pl.webapp.shop.admin.shipment.model.AdminShipment;
import pl.webapp.shop.admin.shipment.model.AdminShipmentType;
import pl.webapp.shop.admin.shipment.service.AdminShipmentService;

import java.util.ArrayList;
import java.util.List;

import static pl.webapp.shop.admin.shipment.controller.mapper.AdminShipmentMapper.mapToAdminShipment;

@RestController
@RequestMapping("/api/v1/admin/shipments")
@RequiredArgsConstructor
class AdminShipmentController {

    private static final Long EMPTY_ID = null;
    private final AdminShipmentService shipmentService;

    @GetMapping
    List<AdminShipment> getShipments() {
        return shipmentService.getShipments();
    }

    @GetMapping("/{id}")
    AdminShipment getShipment(@PathVariable Long id) {
        return shipmentService.getShipment(id);
    }

    @PostMapping
    AdminShipment createShipment(@RequestBody @Valid AdminShipmentDto adminShipmentDto) {
        return shipmentService.createShipment(mapToAdminShipment(adminShipmentDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    AdminShipment updateShipment(@RequestBody @Valid AdminShipmentDto adminShipmentDto, @PathVariable Long id) {
        return shipmentService.updateShipment(mapToAdminShipment(adminShipmentDto, id));
    }

    @DeleteMapping("/{id}")
    void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
    }

    @GetMapping("/initTypes")
    List<String> getShipmentTypes() {
        return createShipmentTypesList();
    }

    private List<String> createShipmentTypesList() {
        List<String> shipmentTypes = new ArrayList<>();
        for (AdminShipmentType type : AdminShipmentType.values()) {
            shipmentTypes.add(type.name());
        }

        return shipmentTypes;
    }
}
