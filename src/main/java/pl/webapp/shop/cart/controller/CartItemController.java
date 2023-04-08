package pl.webapp.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.cart.service.CartItemService;

@RestController
@RequestMapping("/api/v1/cartItems")
@RequiredArgsConstructor
class CartItemController {

    private final CartItemService cartItemService;

    @DeleteMapping("/{id}")
    void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }
}
