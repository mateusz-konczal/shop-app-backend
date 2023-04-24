package pl.webapp.shop.admin.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.order.controller.dto.AdminOrderReadDto;
import pl.webapp.shop.admin.order.model.AdminOrder;
import pl.webapp.shop.admin.order.service.AdminOrderService;

import static pl.webapp.shop.admin.order.controller.mapper.AdminOrderMapper.mapToAdminOrderReadDtoPage;

@RestController
@RequestMapping("/api/v1/admin/orders")
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
}
