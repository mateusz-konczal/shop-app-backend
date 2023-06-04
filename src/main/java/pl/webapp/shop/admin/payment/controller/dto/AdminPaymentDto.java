package pl.webapp.shop.admin.payment.controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import pl.webapp.shop.common.model.PaymentType;

public record AdminPaymentDto(

        @NotBlank
        @Length(min = 4)
        String name,

        PaymentType type,

        boolean defaultPayment,

        String note) {
}
