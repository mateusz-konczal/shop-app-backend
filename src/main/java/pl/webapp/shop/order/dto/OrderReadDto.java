package pl.webapp.shop.order.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderReadDto(

        Long id,
        LocalDateTime placeDate,
        String orderStatus,
        BigDecimal totalValue) {
}
