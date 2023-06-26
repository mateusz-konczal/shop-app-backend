package pl.webapp.shop.common.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductReadDto(

        Long id,
        String name,
        String description,
        BigDecimal price,
        BigDecimal salePrice,
        String currency,
        String image,
        String slug) {
}
