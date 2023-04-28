package pl.webapp.shop.admin.payment.controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import pl.webapp.shop.admin.common.model.AdminPaymentType;

public record AdminPaymentDto(

        @NotBlank
        @Length(min = 4)
        String name,

        AdminPaymentType type,

        boolean defaultPayment,

        String note) {
}
