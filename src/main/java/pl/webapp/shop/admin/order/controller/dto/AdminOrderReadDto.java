package pl.webapp.shop.admin.order.controller.dto;

import lombok.Builder;
import pl.webapp.shop.admin.order.model.AdminOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record AdminOrderReadDto(

        Long id,
        LocalDateTime placeDate,
        AdminOrderStatus orderStatus,
        BigDecimal totalValue) {
}
