package pl.webapp.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.cart.repository.CartItemRepository;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
}
