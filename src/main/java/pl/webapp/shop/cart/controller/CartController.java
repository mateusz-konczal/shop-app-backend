package pl.webapp.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.cart.dto.CartProductDto;
import pl.webapp.shop.cart.model.Cart;
import pl.webapp.shop.cart.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    Cart getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PutMapping("/{id}")
    Cart addProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto) {
        return cartService.addProductToCart(id, cartProductDto);
    }
}
