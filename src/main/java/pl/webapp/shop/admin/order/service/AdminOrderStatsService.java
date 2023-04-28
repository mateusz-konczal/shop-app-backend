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
import java.util.Map;
import java.util.TreeMap;

import static pl.webapp.shop.admin.order.service.mapper.AdminOrderStatsMapper.createAdminOrderStatsDto;

@Service
@RequiredArgsConstructor
public class AdminOrderStatsService {

    private final AdminOrderRepository orderRepository;

    public AdminOrderStatsDto getSalesStatistics() {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        List<AdminOrder> completedOrders = orderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(
                from, to, AdminOrderStatus.COMPLETED);

        Map<Integer, AdminOrderStatsValue> monthlyStats = new TreeMap<>();
        for (int dayOfMonth = from.getDayOfMonth(); dayOfMonth <= to.getDayOfMonth(); dayOfMonth++) {
            monthlyStats.put(dayOfMonth, aggregateValues(dayOfMonth, completedOrders));
        }

        return createAdminOrderStatsDto(monthlyStats);
    }

    private AdminOrderStatsValue aggregateValues(int dayOfMonth, List<AdminOrder> completedOrders) {
        Long orderCounter = 0L;
        BigDecimal sumOfTotalValue = BigDecimal.ZERO;
        for (AdminOrder order : completedOrders) {
            if (dayOfMonth == order.getPlaceDate().getDayOfMonth()) {
                orderCounter++;
                sumOfTotalValue = sumOfTotalValue.add(order.getTotalValue());
            }
        }

        return new AdminOrderStatsValue(orderCounter, sumOfTotalValue);
    }
}
