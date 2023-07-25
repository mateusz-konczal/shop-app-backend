package pl.webapp.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.cart.controller.dto.CartSummaryDto;
import pl.webapp.shop.cart.dto.CartItemDto;
import pl.webapp.shop.cart.service.CartService;

import java.util.List;

import static pl.webapp.shop.cart.controller.mapper.CartMapper.mapToCartSummaryDto;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
class CartController {

    private final CartService cartService;

    @GetMapping("/{uuid}")
    CartSummaryDto getCart(@PathVariable String uuid) {
        return mapToCartSummaryDto(cartService.getCart(uuid));
    }

    @PutMapping
    CartSummaryDto addProductToCart(@RequestParam(required = false) String uuid, @RequestBody CartItemDto cartItemDto) {
        return mapToCartSummaryDto(cartService.addProductToCart(uuid, cartItemDto));
    }

    @PutMapping("/{uuid}/update")
    CartSummaryDto updateCart(@PathVariable String uuid, @RequestBody List<CartItemDto> cartItemDtoList) {
        return mapToCartSummaryDto(cartService.updateCart(uuid, cartItemDtoList));
    }
}
