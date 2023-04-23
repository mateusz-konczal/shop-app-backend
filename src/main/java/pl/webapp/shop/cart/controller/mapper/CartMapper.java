package pl.webapp.shop.cart.controller.mapper;

import pl.webapp.shop.cart.controller.dto.CartItemSummaryDto;
import pl.webapp.shop.cart.controller.dto.CartProductDto;
import pl.webapp.shop.cart.controller.dto.CartSummaryDto;
import pl.webapp.shop.cart.controller.dto.SummaryDto;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.common.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CartMapper {

    private CartMapper() {
    }

    public static CartSummaryDto mapToCartSummaryDto(Cart cart) {
        return CartSummaryDto.builder()
                .id(cart.getId())
                .items(mapToListCartItemSummaryDto(cart.getItems()))
                .summary(mapToSummaryDto(cart.getItems()))
                .build();
    }

    private static List<CartItemSummaryDto> mapToListCartItemSummaryDto(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::mapToCartItemSummaryDto)
                .toList();
    }

    private static CartItemSummaryDto mapToCartItemSummaryDto(CartItem cartItem) {
        return CartItemSummaryDto.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(mapToCartProductDto(cartItem.getProduct()))
                .lineValue(calculateLineValue(cartItem))
                .build();
    }

    private static CartProductDto mapToCartProductDto(Product product) {
        return CartProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .build();
    }

    private static BigDecimal calculateLineValue(CartItem cartItem) {
        return cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private static SummaryDto mapToSummaryDto(List<CartItem> items) {
        return SummaryDto.builder()
                .totalValue(sumValues(items).setScale(2, RoundingMode.HALF_UP))
                .build();
    }

    private static BigDecimal sumValues(List<CartItem> items) {
        return items.stream()
                .map(CartMapper::calculateLineValue)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
