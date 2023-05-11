package pl.webapp.shop.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.common.mail.MailClientService;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.repository.CartItemRepository;
import pl.webapp.shop.common.repository.CartRepository;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.OrderRow;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.Shipment;
import pl.webapp.shop.order.repository.OrderRepository;
import pl.webapp.shop.order.repository.OrderRowRepository;
import pl.webapp.shop.order.repository.PaymentRepository;
import pl.webapp.shop.order.repository.ShipmentRepository;

import java.util.List;

import static pl.webapp.shop.order.service.mapper.OrderMailContentMapper.createMailContent;
import static pl.webapp.shop.order.service.mapper.OrderMapper.createOrder;
import static pl.webapp.shop.order.service.mapper.OrderMapper.createOrderSummaryDto;
import static pl.webapp.shop.order.service.mapper.OrderMapper.mapToOrderRowWithProduct;
import static pl.webapp.shop.order.service.mapper.OrderMapper.mapToOrderRowWithShipment;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final MailClientService mailClientService;

    @Transactional
    public OrderSummaryDto placeOrder(OrderDto orderDto, String userUuid) {
        Cart cart = cartRepository.findById(orderDto.cartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.shipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.paymentId()).orElseThrow();
        Order order = orderRepository.save(createOrder(orderDto, cart, shipment, payment, userUuid));
        saveOrderRows(order.getId(), cart, shipment);
        deleteOrderCart(orderDto.cartId());
        log.info("Order has been placed");
        sendConfirmationMail(order);

        return createOrderSummaryDto(order, shipment, payment);
    }

    private void saveOrderRows(Long orderId, Cart cart, Shipment shipment) {
        saveProductRows(orderId, cart);
        saveShipmentRow(orderId, shipment);
    }

    private void saveProductRows(Long orderId, Cart cart) {
        List<OrderRow> orderRows = cart.getItems().stream()
                .map(cartItem -> mapToOrderRowWithProduct(orderId, cartItem))
                .toList();
        orderRowRepository.saveAll(orderRows);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRowWithShipment(orderId, shipment));
    }

    private void deleteOrderCart(Long cartId) {
        cartItemRepository.deleteAllByCartId(cartId);
        cartRepository.deleteCartById(cartId);
    }

    private void sendConfirmationMail(Order order) {
        mailClientService.getInstance()
                .send(order.getEmail(), "Otrzymaliśmy Twoje zamówienie", createMailContent(order));
    }
}
