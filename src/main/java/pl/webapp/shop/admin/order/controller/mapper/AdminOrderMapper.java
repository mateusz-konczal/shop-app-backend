package pl.webapp.shop.admin.order.controller.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import pl.webapp.shop.admin.order.controller.dto.AdminOrderReadDto;
import pl.webapp.shop.admin.order.model.AdminOrder;

import java.util.List;

public class AdminOrderMapper {

    private AdminOrderMapper() {
    }

    public static Page<AdminOrderReadDto> mapToAdminOrderReadDtoPage(Page<AdminOrder> adminOrderPage) {
        return new PageImpl<>(mapToAdminOrderReadDtoList(adminOrderPage.getContent()),
                adminOrderPage.getPageable(), adminOrderPage.getTotalElements());
    }

    private static List<AdminOrderReadDto> mapToAdminOrderReadDtoList(List<AdminOrder> adminOrderList) {
        return adminOrderList.stream()
                .map(AdminOrderMapper::mapToAdminOrderReadDto)
                .toList();
    }

    private static AdminOrderReadDto mapToAdminOrderReadDto(AdminOrder adminOrder) {
        return AdminOrderReadDto.builder()
                .id(adminOrder.getId())
                .placeDate(adminOrder.getPlaceDate())
                .orderStatus(adminOrder.getOrderStatus())
                .totalValue(adminOrder.getTotalValue())
                .build();
    }
}
