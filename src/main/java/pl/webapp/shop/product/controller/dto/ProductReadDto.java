package pl.webapp.shop.product.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductReadDto(

        Long id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        String image,
        String slug) {
}
