package pl.webapp.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.cart.dto.CartItemDto;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.repository.CartRepository;
import pl.webapp.shop.common.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(Long id, CartItemDto cartItemDto) {
        Cart cart = getInitializedCart(id);
        cart.addProduct(CartItem.builder()
                .created(LocalDateTime.now())
                .quantity(cartItemDto.quantity())
                .product(getProduct(cartItemDto.productId()))
                .cartId(cart.getId())
                .build());

        return cart;
    }

    @Transactional
    public Cart updateCart(Long id, List<CartItemDto> cartItemDtoList) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getItems().forEach(cartItem -> cartItemDtoList.stream()
                .filter(cartItemDto -> cartItem.getProduct().getId().equals(cartItemDto.productId()))
                .findFirst()
                .ifPresent(cartItemDto -> cartItem.setQuantity(cartItemDto.quantity())));

        return cart;
    }

    private Cart getInitializedCart(Long id) {
        if (id == null || id <= 0) {
            return saveNewCart();
        }

        return cartRepository.findById(id).orElseGet(this::saveNewCart);
    }

    private Cart saveNewCart() {
        return cartRepository.save(Cart.builder().created(LocalDateTime.now()).build());
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
