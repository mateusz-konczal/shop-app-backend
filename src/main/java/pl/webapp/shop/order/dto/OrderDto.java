package pl.webapp.shop.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record OrderDto(

        @NotBlank
        @Length(max = 64)
        String firstName,

        @NotBlank
        @Length(max = 64)
        String lastName,

        @NotBlank
        @Length(max = 80)
        String street,

        @NotBlank
        @Length(max = 6)
        String houseNumber,

        @Length(max = 6)
        String apartmentNumber,

        @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
        String zipCode,

        @NotBlank
        @Length(max = 64)
        String city,

        @NotBlank
        @Email
        @Length(max = 64)
        String email,

        @NotBlank
        @Length(max = 16)
        String phone,

        @NotNull
        Long cartId,

        @NotNull
        Long shipmentId,

        @NotNull
        Long paymentId) {
}
