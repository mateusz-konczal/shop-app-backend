package pl.webapp.shop.cart.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CartProductDto(

        Long id,
        String name,
        BigDecimal price,
        String currency,
        String image,
        String slug) {
}
