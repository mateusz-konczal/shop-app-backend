package pl.webapp.shop.product.service.dto;

import lombok.Builder;

@Builder
public record ReviewReadDto(

        Long id,
        Long productId,
        String authorName,
        String content,
        boolean moderated) {
}
