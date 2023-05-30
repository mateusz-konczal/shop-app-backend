package pl.webapp.shop.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.common.model.OrderStatus;
import pl.webapp.shop.order.controller.dto.InitOrderDto;
import pl.webapp.shop.order.controller.dto.PaymentNotificationDto;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderReadDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.dto.TransactionNotificationDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.service.OrderService;
import pl.webapp.shop.order.service.PaymentService;
import pl.webapp.shop.order.service.ShipmentService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    @PostMapping
    OrderSummaryDto placeOrder(@RequestBody @Valid OrderDto orderDto, @AuthenticationPrincipal String userUuid) {
        return orderService.placeOrder(orderDto, userUuid);
    }

    @GetMapping("/initOrder")
    InitOrderDto initOrder() {
        return InitOrderDto.builder()
                .shipments(shipmentService.getShipments())
                .payments(paymentService.getPayments())
                .build();
    }

    @GetMapping
    List<OrderReadDto> getOrders(@AuthenticationPrincipal String userUuid) {
        return orderService.getOrdersForCustomer(userUuid);
    }

    @GetMapping("/notification/{orderHash}")
    PaymentNotificationDto showNotification(@PathVariable @Length(max = 12) String orderHash) {
        Order order = orderService.getOrderByOrderHash(orderHash);
        return new PaymentNotificationDto(order.getOrderStatus() == OrderStatus.PAID);
    }

    @PostMapping("/notification/{orderHash}")
    void receiveNotification(@PathVariable @Length(max = 12) String orderHash,
                             @RequestBody TransactionNotificationDto notificationDto) {
        orderService.receiveNotification(orderHash, notificationDto);
    }
}
