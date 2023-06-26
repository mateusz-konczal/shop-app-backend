package pl.webapp.shop.common.dto;

import lombok.Builder;

@Builder
public record ReviewReadDto(

        Long id,
        Long productId,
        String authorName,
        String content,
        boolean moderated) {
}
