package pl.webapp.shop.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.order.controller.dto.InitOrderDto;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.service.OrderService;
import pl.webapp.shop.order.service.ShipmentService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;

    @PostMapping
    OrderSummaryDto placeOrder(@RequestBody @Valid OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/initOrder")
    public InitOrderDto initOrder() {
        return InitOrderDto.builder()
                .shipments(shipmentService.getShipments())
                .build();
    }
}
