package pl.webapp.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.order.controller.dto.AdminOrderReadDto;
import pl.webapp.shop.admin.order.controller.dto.AdminOrderStatusesDto;
import pl.webapp.shop.admin.order.model.AdminOrder;
import pl.webapp.shop.admin.order.service.AdminOrderService;
import pl.webapp.shop.common.model.OrderStatus;

import java.util.HashMap;
import java.util.Map;

import static pl.webapp.shop.admin.order.controller.mapper.AdminOrderMapper.mapToAdminOrderReadDtoPage;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
class AdminOrderController {

    private final AdminOrderService orderService;

    @GetMapping
    Page<AdminOrderReadDto> getOrders(Pageable pageable) {
        return mapToAdminOrderReadDtoPage(orderService.getOrders(pageable));
    }

    @GetMapping("/{id}")
    AdminOrder getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PatchMapping("/{id}")
    void patchOrder(@PathVariable Long id, @RequestBody Map<String, String> values) {
        orderService.patchOrder(id, values);
    }

    @GetMapping("/initStatuses")
    AdminOrderStatusesDto getOrderStatuses() {
        return new AdminOrderStatusesDto(createOrderStatusesMap());
    }

    private Map<String, String> createOrderStatusesMap() {
        HashMap<String, String> orderStatuses = new HashMap<>();
        for (OrderStatus status : OrderStatus.values()) {
            orderStatuses.put(status.name(), status.getValue());
        }

        return orderStatuses;
    }
}
