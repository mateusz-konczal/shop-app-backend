package pl.webapp.shop.admin.payment.controller.mapper;

import pl.webapp.shop.admin.common.model.AdminPayment;
import pl.webapp.shop.admin.payment.controller.dto.AdminPaymentDto;

public class AdminPaymentMapper {

    private AdminPaymentMapper() {
    }

    public static AdminPayment mapToAdminPayment(AdminPaymentDto adminPaymentDto, Long id) {
        return AdminPayment.builder()
                .id(id)
                .name(adminPaymentDto.name())
                .type(adminPaymentDto.type())
                .defaultPayment(adminPaymentDto.defaultPayment())
                .enabled(adminPaymentDto.enabled())
                .note(adminPaymentDto.note())
                .build();
    }
}
