package pl.webapp.shop.admin.shipment.controller.mapper;

import pl.webapp.shop.admin.shipment.controller.dto.AdminShipmentDto;
import pl.webapp.shop.admin.shipment.model.AdminShipment;

public class AdminShipmentMapper {

    private AdminShipmentMapper() {
    }

    public static AdminShipment mapToAdminShipment(AdminShipmentDto adminShipmentDto, Long id) {
        return AdminShipment.builder()
                .id(id)
                .name(adminShipmentDto.name())
                .price(adminShipmentDto.price())
                .type(adminShipmentDto.type())
                .defaultShipment(adminShipmentDto.defaultShipment())
                .build();
    }
}
