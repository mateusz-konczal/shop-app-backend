package pl.webapp.shop.cart.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CartSummaryDto(

        String uuid,
        List<CartItemSummaryDto> items,
        SummaryDto summary) {
}
