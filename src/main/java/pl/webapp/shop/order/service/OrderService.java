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
import pl.webapp.shop.order.repository.OrderRepository;
import pl.webapp.shop.order.repository.OrderRowRepository;

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

    @Transactional
    public OrderSummaryDto placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow();
        Order order = orderRepository.save(mapToOrder(orderDto, cart.getItems()));
        saveOrderRows(cart, order.getId());
        cartItemRepository.deleteAllByCartId(orderDto.cartId());
        cartRepository.deleteCartById(orderDto.cartId());

        return mapToOrderSummaryDto(order);
    }

    private void saveOrderRows(Cart cart, Long orderId) {
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
}
