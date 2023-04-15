package pl.webapp.shop.order.dto;

import lombok.Builder;
import pl.webapp.shop.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderSummaryDto(

        Long id,
        LocalDateTime placeDate,
        OrderStatus status,
        BigDecimal totalValue) {
}
