package pl.webapp.shop.admin.order.controller.dto;

import lombok.Builder;
import pl.webapp.shop.common.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record AdminOrderReadDto(

        Long id,
        LocalDateTime placeDate,
        OrderStatus orderStatus,
        BigDecimal totalValue) {
}
