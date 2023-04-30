package pl.webapp.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.admin.order.dto.AdminOrderStatsDto;
import pl.webapp.shop.admin.order.model.AdminOrder;
import pl.webapp.shop.admin.order.model.AdminOrderStatus;
import pl.webapp.shop.admin.order.repository.AdminOrderRepository;
import pl.webapp.shop.admin.order.service.dto.AdminOrderStatsValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static pl.webapp.shop.admin.order.service.mapper.AdminOrderStatsMapper.createAdminOrderStatsDto;

@Service
@RequiredArgsConstructor
public class AdminOrderStatsService {

    private final AdminOrderRepository orderRepository;

    public AdminOrderStatsDto getSalesStatistics() {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        List<AdminOrder> orders = orderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(
                from, to, AdminOrderStatus.COMPLETED);

        List<AdminOrderStatsValue> stats = IntStream.rangeClosed(from.getDayOfMonth(), to.getDayOfMonth())
                .boxed()
                .map(day -> aggregateValues(day, orders))
                .toList();

        return createAdminOrderStatsDto(stats);
    }

    private AdminOrderStatsValue aggregateValues(Integer day, List<AdminOrder> orders) {
        return orders.stream().filter(order -> order.getPlaceDate().getDayOfMonth() == day)
                .map(AdminOrder::getTotalValue)
                .reduce(new AdminOrderStatsValue(day, 0L, BigDecimal.ZERO),
                        (AdminOrderStatsValue statsValue, BigDecimal totalValue) -> new AdminOrderStatsValue(day,
                                statsValue.numberOfOrders() + 1, statsValue.sales().add(totalValue)),
                        (statsValue, statsValue2) -> null);
    }
}
