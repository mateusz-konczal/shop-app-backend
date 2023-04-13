package pl.webapp.shop.cart.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartItemSummaryDto(

        Long id,
        int quantity,
        CartProductDto product,
        BigDecimal lineValue) {
}
