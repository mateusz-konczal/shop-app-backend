package pl.webapp.shop.cart.controller.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SummaryDto(BigDecimal totalValue) {
}
