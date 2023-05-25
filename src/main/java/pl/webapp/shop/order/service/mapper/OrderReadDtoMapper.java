package pl.webapp.shop.order.service.mapper;

import pl.webapp.shop.order.dto.OrderReadDto;
import pl.webapp.shop.order.model.Order;

import java.util.List;

public class OrderReadDtoMapper {

    private OrderReadDtoMapper() {
    }

    public static List<OrderReadDto> mapToOrderReadDtoList(List<Order> orders) {
        return orders.stream()
                .map(OrderReadDtoMapper::mapToOrderReadDto)
                .toList();
    }

    private static OrderReadDto mapToOrderReadDto(Order order) {
        return OrderReadDto.builder()
                .id(order.getId())
                .placeDate(order.getPlaceDate())
                .orderStatus(order.getOrderStatus().getValue())
                .totalValue(order.getTotalValue())
                .build();
    }
}
