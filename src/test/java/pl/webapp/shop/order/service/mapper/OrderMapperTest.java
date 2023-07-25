package pl.webapp.shop.order.service.mapper;

import org.junit.jupiter.api.Test;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.common.model.OrderStatus;
import pl.webapp.shop.common.model.PaymentType;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.model.ShipmentType;
import pl.webapp.shop.order.dto.OrderDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.Shipment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OrderMapperTest {

    private static final Long CART_ID = 11L;
    private static final String CART_UUID = "ff80bcce-37e6-4fd8-8243-ab8db4616f54";
    private static final Long SHIPMENT_ID = 3L;
    private static final Long PAYMENT_ID = 4L;

    @Test
    void shouldCreateOrder() {
        // GIVEN
        OrderDto orderDto = getOrderDto();
        Cart cart = getCart();
        Shipment shipment = getShipment();
        Payment payment = getPayment();
        String userUuid = UUID.randomUUID().toString();
        // WHEN
        Order result = OrderMapper.createOrder(orderDto, cart, shipment, payment, userUuid);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getOrderStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(result.getTotalValue()).isEqualTo(new BigDecimal("51.87"));
        assertThat(result.getFirstName()).isEqualTo("Philip");
        assertThat(result.getLastName()).isEqualTo("Trevino");
        assertThat(result.getEmail()).isEqualTo("philip.trevino@email.pl");
        assertThat(result.getPayment()).isNotNull();
    }

    private OrderDto getOrderDto() {
        return OrderDto.builder()
                .firstName("Philip")
                .lastName("Trevino")
                .street("Bridle St.")
                .houseNumber("128c")
                .apartmentNumber("27")
                .zipCode("61-604")
                .city("Peoria")
                .email("philip.trevino@email.pl")
                .phone("457 751 026")
                .cartUuid(CART_UUID)
                .paymentId(PAYMENT_ID)
                .shipmentId(SHIPMENT_ID)
                .build();
    }

    private Cart getCart() {
        return Cart.builder()
                .id(CART_ID)
                .uuid(CART_UUID)
                .created(LocalDateTime.now())
                .items(getItems())
                .build();
    }

    private List<CartItem> getItems() {
        return List.of(
                CartItem.builder()
                        .id(33L)
                        .quantity(1)
                        .product(Product.builder()
                                .id(5L)
                                .price(new BigDecimal("2.34"))
                                .build())
                        .cartId(CART_ID)
                        .build(),
                CartItem.builder()
                        .id(34L)
                        .quantity(2)
                        .product(Product.builder()
                                .id(7L)
                                .price(new BigDecimal("14.77"))
                                .build())
                        .cartId(CART_ID)
                        .build()
        );
    }

    private Shipment getShipment() {
        return Shipment.builder()
                .id(SHIPMENT_ID)
                .name("Premium test shipment")
                .price(new BigDecimal("19.99"))
                .type(ShipmentType.DELIVERYMAN)
                .build();
    }

    private Payment getPayment() {
        return Payment.builder()
                .id(PAYMENT_ID)
                .name("Premium test payment")
                .type(PaymentType.BANK_TRANSFER)
                .build();
    }
}
