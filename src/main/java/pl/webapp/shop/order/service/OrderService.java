package pl.webapp.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.repository.CartItemRepository;
import pl.webapp.shop.common.repository.CartRepository;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.OrderRow;
import pl.webapp.shop.order.model.Shipment;
import pl.webapp.shop.order.repository.OrderRepository;
import pl.webapp.shop.order.repository.OrderRowRepository;
import pl.webapp.shop.order.repository.ShipmentRepository;

import java.util.List;

import static pl.webapp.shop.order.service.mapper.OrderMapper.mapToOrder;
import static pl.webapp.shop.order.service.mapper.OrderMapper.mapToOrderSummaryDto;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;

    @Transactional
    public OrderSummaryDto placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.shipmentId()).orElseThrow();
        Order order = orderRepository.save(mapToOrder(orderDto, cart.getItems(), shipment.getPrice()));
        saveOrderRows(cart, order.getId(), shipment);

        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());

        return mapToOrderSummaryDto(order, shipment.getName());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveProductRows(Cart cart, Long orderId) {
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
