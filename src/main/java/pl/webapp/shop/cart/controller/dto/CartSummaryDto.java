package pl.webapp.shop.cart.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CartSummaryDto(

        Long id,
        List<CartItemSummaryDto> items,
        SummaryDto summary) {
}
