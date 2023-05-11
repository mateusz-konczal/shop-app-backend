package pl.webapp.shop.order.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.webapp.shop.common.mail.FakeMailService;
import pl.webapp.shop.common.mail.MailClientService;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.repository.CartItemRepository;
import pl.webapp.shop.common.repository.CartRepository;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.OrderRow;
import pl.webapp.shop.order.model.OrderStatus;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.PaymentType;
import pl.webapp.shop.order.model.Shipment;
import pl.webapp.shop.order.model.ShipmentType;
import pl.webapp.shop.order.repository.OrderRepository;
import pl.webapp.shop.order.repository.OrderRowRepository;
import pl.webapp.shop.order.repository.PaymentRepository;
import pl.webapp.shop.order.repository.ShipmentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private static final Long CART_ID = 7L;
    private static final Long SHIPMENT_ID = 1L;
    private static final String SHIPMENT_NAME = "Test shipment";
    private static final Long PAYMENT_ID = 2L;
    private static final String PAYMENT_NAME = "Test payment";

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderRowRepository orderRowRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private MailClientService mailClientService;
    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceOrder() {
        // GIVEN
        OrderDto orderDto = getOrderDto();
        String userUuid = UUID.randomUUID().toString();
        given(cartRepository.findById(anyLong())).willReturn(Optional.of(getCart()));
        given(shipmentRepository.findById(anyLong())).willReturn(Optional.of(getShipment()));
        given(paymentRepository.findById(anyLong())).willReturn(Optional.of(getPayment()));
        given(orderRepository.save(any(Order.class))).willAnswer(invocation -> invocation.getArguments()[0]);
        given(mailClientService.getInstance()).willReturn(new FakeMailService());
        // WHEN
        OrderSummaryDto result = orderService.placeOrder(orderDto, userUuid);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.status()).isEqualTo(OrderStatus.NEW);
        assertThat(result.totalValue()).isEqualTo(new BigDecimal("39.42"));
        assertThat(result.shipment().getId()).isEqualTo(SHIPMENT_ID);
        assertThat(result.shipment().getName()).isEqualTo(SHIPMENT_NAME);
        assertThat(result.shipment().getType()).isEqualTo(ShipmentType.DELIVERYMAN);
        assertThat(result.payment().getId()).isEqualTo(PAYMENT_ID);
        assertThat(result.payment().getName()).isEqualTo(PAYMENT_NAME);
        assertThat(result.payment().getType()).isEqualTo(PaymentType.BANK_TRANSFER);
        // checking for side effects
        InOrder inOrder = inOrder(orderRowRepository);
        inOrder.verify(orderRowRepository, times(1)).saveAll(anyList());
        inOrder.verify(orderRowRepository, times(1)).save(any(OrderRow.class));
        verify(cartItemRepository, times(1)).deleteAllByCartId(CART_ID);
        verify(cartRepository, times(1)).deleteCartById(CART_ID);
    }

    private OrderDto getOrderDto() {
        return OrderDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .street("street")
                .houseNumber("houseNumber")
                .apartmentNumber("apartmentNumber")
                .zipCode("zipCode")
                .city("city")
                .email("email@email.pl")
                .phone("phone")
                .cartId(CART_ID)
                .paymentId(PAYMENT_ID)
                .shipmentId(SHIPMENT_ID)
                .build();
    }

    private Cart getCart() {
        return Cart.builder()
                .id(CART_ID)
                .created(LocalDateTime.now())
                .items(getItems())
                .build();
    }

    private List<CartItem> getItems() {
        return List.of(
                CartItem.builder()
                        .id(23L)
                        .quantity(1)
                        .product(Product.builder()
                                .id(1L)
                                .price(new BigDecimal("5.67"))
                                .build())
                        .cartId(CART_ID)
                        .build(),
                CartItem.builder()
                        .id(24L)
                        .quantity(2)
                        .product(Product.builder()
                                .id(2L)
                                .price(new BigDecimal("9.38"))
                                .build())
                        .cartId(CART_ID)
                        .build()
        );
    }

    private Shipment getShipment() {
        return Shipment.builder()
                .id(SHIPMENT_ID)
                .name(SHIPMENT_NAME)
                .price(new BigDecimal("14.99"))
                .type(ShipmentType.DELIVERYMAN)
                .defaultShipment(true)
                .build();
    }

    private Payment getPayment() {
        return Payment.builder()
                .id(PAYMENT_ID)
                .name(PAYMENT_NAME)
                .type(PaymentType.BANK_TRANSFER)
                .defaultPayment(true)
                .build();
    }
}