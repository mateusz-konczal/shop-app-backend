package pl.webapp.shop.product.dto;

import lombok.Builder;
import pl.webapp.shop.common.dto.ReviewReadDto;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductReviewsDto(

        Long id,
        String name,
        String description,
        String fullDescription,
        BigDecimal price,
        BigDecimal salePrice,
        String currency,
        String image,
        String slug,
        List<ReviewReadDto> reviews) {
}
