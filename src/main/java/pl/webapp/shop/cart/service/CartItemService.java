package pl.webapp.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.common.repository.CartItemRepository;
import pl.webapp.shop.common.repository.CartRepository;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    public Long countItemsInCart(String cartUuid) {
        return cartItemRepository.countByCartId(cartRepository.findByUuid(cartUuid).orElseThrow().getId());
    }
}
