package pl.webapp.shop.order.dto;

import lombok.Builder;
import pl.webapp.shop.common.model.OrderStatus;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.Shipment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderSummaryDto(

        Long id,
        LocalDateTime placeDate,
        OrderStatus status,
        BigDecimal totalValue,
        Shipment shipment,
        Payment payment) {
}
