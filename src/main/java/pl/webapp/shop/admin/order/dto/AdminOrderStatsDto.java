package pl.webapp.shop.admin.order.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Builder
public record AdminOrderStatsDto(

        Set<Integer> labels,
        List<Long> orders,
        List<BigDecimal> sales) {
}
