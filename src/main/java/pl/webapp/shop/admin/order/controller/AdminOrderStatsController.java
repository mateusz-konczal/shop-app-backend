package pl.webapp.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.order.dto.AdminOrderStatsDto;
import pl.webapp.shop.admin.order.service.AdminOrderStatsService;

@RestController
@RequestMapping("/admin/orders/stats")
@RequiredArgsConstructor
class AdminOrderStatsController {

    private final AdminOrderStatsService orderStatsService;

    @GetMapping
    AdminOrderStatsDto getSalesStatistics() {
        return orderStatsService.getSalesStatistics();
    }
}
