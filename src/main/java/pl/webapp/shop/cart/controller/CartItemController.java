package pl.webapp.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.cart.service.CartItemService;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
class CartItemController {

    private final CartItemService cartItemService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }

    @GetMapping("/count/{cartUuid}")
    Long countItemsInCart(@PathVariable String cartUuid) {
        return cartItemService.countItemsInCart(cartUuid);
    }
}
