package pl.webapp.shop.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderDto(

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String street,

        @NotBlank
        String zipCode,

        @NotBlank
        String city,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotNull
        Long cartId,

        @NotNull
        Long shipmentId) {
}
