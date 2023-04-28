package pl.webapp.shop.admin.order.service.mapper;

import pl.webapp.shop.admin.order.dto.AdminOrderStatsDto;
import pl.webapp.shop.admin.order.service.dto.AdminOrderStatsValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class AdminOrderStatsMapper {

    private AdminOrderStatsMapper() {
    }

    public static AdminOrderStatsDto createAdminOrderStatsDto(Map<Integer, AdminOrderStatsValue> monthlyStats) {
        List<Long> ordersList = monthlyStats.values().stream().map(AdminOrderStatsValue::numberOfOrders).toList();
        List<BigDecimal> salesList = monthlyStats.values().stream().map(AdminOrderStatsValue::sales).toList();

        return AdminOrderStatsDto.builder()
                .labelsSet(monthlyStats.keySet())
                .ordersList(ordersList)
                .salesList(salesList)
                .totalNumberOfOrders(ordersList.stream().reduce(Long::sum).orElse(0L))
                .totalSales(salesList.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                .build();
    }
}
