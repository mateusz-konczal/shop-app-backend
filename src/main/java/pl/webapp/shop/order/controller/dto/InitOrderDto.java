package pl.webapp.shop.order.controller.dto;

import lombok.Builder;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.Shipment;

import java.util.List;

@Builder
public record InitOrderDto(

        List<Shipment> shipments,
        List<Payment> payments) {
}
