package pl.webapp.shop.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemDto(

        @NotNull
        Long productId,

        @NotNull
        @Min(1)
        int quantity) {
}
