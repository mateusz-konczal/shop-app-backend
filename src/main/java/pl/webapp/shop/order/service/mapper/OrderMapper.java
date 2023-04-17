package pl.webapp.shop.order.service.mapper;

import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.OrderStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.time.LocalDateTime.now;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderSummaryDto mapToOrderSummaryDto(Order order, String shipmentName) {
        return OrderSummaryDto.builder()
                .id(order.getId())
                .placeDate(order.getPlaceDate())
                .status(order.getOrderStatus())
                .totalValue(order.getTotalValue())
                .shipmentName(shipmentName)
                .build();
    }

    public static Order mapToOrder(OrderDto orderDto, List<CartItem> items, BigDecimal shipmentPrice) {
        return Order.builder()
                .placeDate(now())
                .orderStatus(OrderStatus.NEW)
                .totalValue(calculateTotalValue(items, shipmentPrice))
                .firstName(orderDto.firstName())
                .lastName(orderDto.lastName())
                .street(orderDto.street())
                .zipCode(orderDto.zipCode())
                .city(orderDto.city())
                .email(orderDto.email())
                .phone(orderDto.phone())
                .build();
    }

    private static BigDecimal calculateTotalValue(List<CartItem> items, BigDecimal shipmentPrice) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipmentPrice)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
