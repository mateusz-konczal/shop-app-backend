package pl.webapp.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.common.repository.CartItemRepository;
import pl.webapp.shop.common.repository.CartRepository;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.OrderRow;
import pl.webapp.shop.order.model.OrderStatus;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.Shipment;
import pl.webapp.shop.order.repository.OrderRepository;
import pl.webapp.shop.order.repository.OrderRowRepository;
import pl.webapp.shop.order.repository.PaymentRepository;
import pl.webapp.shop.order.repository.ShipmentRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static java.time.LocalDateTime.now;
import static pl.webapp.shop.order.service.mapper.OrderMapper.mapToOrderSummaryDto;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public OrderSummaryDto placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.shipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.paymentId()).orElseThrow();
        Order order = Order.builder()
                .placeDate(now())
                .orderStatus(OrderStatus.NEW)
                .totalValue(calculateTotalValue(cart.getItems(), shipment.getPrice()))
                .firstName(orderDto.firstName())
                .lastName(orderDto.lastName())
                .street(orderDto.street())
                .zipCode(orderDto.zipCode())
                .city(orderDto.city())
                .email(orderDto.email())
                .phone(orderDto.phone())
                .payment(payment)
                .build();
        Order savedOrder = orderRepository.save(order);
        saveOrderRows(savedOrder.getId(), cart, shipment);
        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());

        return mapToOrderSummaryDto(savedOrder, shipment, payment);
    }

    private static BigDecimal calculateTotalValue(List<CartItem> items, BigDecimal shipmentPrice) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipmentPrice)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private void saveOrderRows(Long orderId, Cart cart, Shipment shipment) {
        saveProductRows(orderId, cart);
        saveShipmentRow(orderId, shipment);
    }

    private void saveProductRows(Long orderId, Cart cart) {
        List<OrderRow> orderRows = cart.getItems().stream()
                .map(cartItem -> OrderRow.builder()
                        .orderId(orderId)
                        .productId(cartItem.getProduct().getId())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getProduct().getPrice())
                        .build())
                .toList();
        orderRowRepository.saveAll(orderRows);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(OrderRow.builder()
                .orderId(orderId)
                .shipmentId(shipment.getId())
                .quantity(1)
                .price(shipment.getPrice())
                .build());
    }
}
