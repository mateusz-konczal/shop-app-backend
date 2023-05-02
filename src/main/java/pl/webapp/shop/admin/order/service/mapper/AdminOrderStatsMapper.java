package pl.webapp.shop.admin.order.service.mapper;

import pl.webapp.shop.admin.order.dto.AdminOrderStatsDto;
import pl.webapp.shop.admin.order.service.dto.AdminOrderStatsValue;

import java.math.BigDecimal;
import java.util.List;

public class AdminOrderStatsMapper {

    private AdminOrderStatsMapper() {
    }

    public static AdminOrderStatsDto createAdminOrderStatsDto(List<AdminOrderStatsValue> stats) {
        List<Long> ordersList = stats.stream().map(AdminOrderStatsValue::numberOfOrders).toList();
        List<BigDecimal> salesList = stats.stream().map(AdminOrderStatsValue::sales).toList();

        return AdminOrderStatsDto.builder()
                .labelsList(stats.stream().map(AdminOrderStatsValue::day).toList())
                .ordersList(ordersList)
                .salesList(salesList)
                .totalNumberOfOrders(ordersList.stream().reduce(Long::sum).orElse(0L))
                .totalSales(salesList.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                .build();
    }
}
