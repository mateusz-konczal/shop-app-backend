package pl.webapp.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.cart.controller.dto.CartSummaryDto;
import pl.webapp.shop.cart.dto.CartItemDto;
import pl.webapp.shop.cart.service.CartService;

import java.util.List;

import static pl.webapp.shop.cart.controller.mapper.CartMapper.mapToCartSummaryDto;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    CartSummaryDto getCart(@PathVariable Long id) {
        return mapToCartSummaryDto(cartService.getCart(id));
    }

    @PutMapping("/{id}")
    CartSummaryDto addProductToCart(@PathVariable Long id, @RequestBody CartItemDto cartItemDto) {
        return mapToCartSummaryDto(cartService.addProductToCart(id, cartItemDto));
    }

    @PutMapping("/{id}/update")
    CartSummaryDto updateCart(@PathVariable Long id, @RequestBody List<CartItemDto> cartItemDtoList) {
        return mapToCartSummaryDto(cartService.updateCart(id, cartItemDtoList));
    }
}
