package pl.webapp.shop.admin.order.service.dto;

import java.math.BigDecimal;

public record AdminOrderStatsValue(

        Integer day,
        Long numberOfOrders,
        BigDecimal sales) {
}
