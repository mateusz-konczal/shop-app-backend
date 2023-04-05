package pl.webapp.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.cart.dto.CartProductDto;
import pl.webapp.shop.cart.model.Cart;
import pl.webapp.shop.cart.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart addProductToCart(Long id, CartProductDto cartProductDto) {
        // TODO check if the cart exists
        return null;
    }
}
