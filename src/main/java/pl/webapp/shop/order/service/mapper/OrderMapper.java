package pl.webapp.shop.order.service.mapper;

import pl.webapp.shop.order.dto.OrderSummaryDto;
import pl.webapp.shop.order.model.Order;
import pl.webapp.shop.order.model.Payment;
import pl.webapp.shop.order.model.Shipment;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderSummaryDto mapToOrderSummaryDto(Order order, Shipment shipment, Payment payment) {
        return OrderSummaryDto.builder()
                .id(order.getId())
                .placeDate(order.getPlaceDate())
                .status(order.getOrderStatus())
                .totalValue(order.getTotalValue())
                .shipment(shipment)
                .payment(payment)
                .build();
    }
}
