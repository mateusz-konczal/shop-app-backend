package pl.webapp.shop.admin.order.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record AdminOrderStatsDto(

        List<Integer> labelsList,
        List<Long> ordersList,
        List<BigDecimal> salesList,
        Long totalNumberOfOrders,
        BigDecimal totalSales) {
}
