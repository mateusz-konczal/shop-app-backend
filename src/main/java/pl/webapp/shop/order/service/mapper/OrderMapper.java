package pl.webapp.shop.order.service.mapper;

import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.OrderRow;
import pl.webapp.shop.order.model.OrderStatus;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.Shipment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderRow mapToOrderRowWithProduct(Long orderId, CartItem cartItem) {
        return OrderRow.builder()
                .orderId(orderId)
                .productId(cartItem.getProduct().getId())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getProduct().getPrice())
                .build();
    }

    public static OrderRow mapToOrderRowWithShipment(Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .orderId(orderId)
                .shipmentId(shipment.getId())
                .quantity(1)
                .price(shipment.getPrice())
                .build();
    }

    public static OrderSummaryDto createOrderSummaryDto(Order order, Shipment shipment, Payment payment) {
        return OrderSummaryDto.builder()
                .id(order.getId())
                .placeDate(order.getPlaceDate())
                .status(order.getOrderStatus())
                .totalValue(order.getTotalValue())
                .shipment(shipment)
                .payment(payment)
                .build();
    }

    public static Order createOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment, String userUuid) {
        return Order.builder()
                .placeDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .totalValue(calculateTotalValue(cart.getItems(), shipment.getPrice()))
                .firstName(orderDto.firstName())
                .lastName(orderDto.lastName())
                .street(orderDto.street())
                .houseNumber(orderDto.houseNumber())
                .apartmentNumber(orderDto.apartmentNumber())
                .zipCode(orderDto.zipCode())
                .city(orderDto.city())
                .email(orderDto.email())
                .phone(orderDto.phone())
                .payment(payment)
                .userUuid(userUuid)
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
