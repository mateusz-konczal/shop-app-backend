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
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCart(String uuid) {
        return cartRepository.findByUuid(uuid).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(String uuid, CartItemDto cartItemDto) {
        Cart cart = getInitializedCart(uuid);
        cart.addProduct(CartItem.builder()
                .created(LocalDateTime.now())
                .quantity(cartItemDto.quantity())
                .product(getProduct(cartItemDto.productId()))
                .cartId(cart.getId())
                .build());

        return cart;
    }

    @Transactional
    public Cart updateCart(String uuid, List<CartItemDto> cartItemDtoList) {
        Cart cart = cartRepository.findByUuid(uuid).orElseThrow();
        cart.getItems().forEach(cartItem -> cartItemDtoList.stream()
                .filter(cartItemDto -> cartItem.getProduct().getId().equals(cartItemDto.productId()))
                .findFirst()
                .ifPresent(cartItemDto -> cartItem.setQuantity(cartItemDto.quantity())));

        return cart;
    }

    private Cart getInitializedCart(String uuid) {
        if (uuid == null || Objects.equals(uuid, "")) {
            return saveNewCart();
        }

        return cartRepository.findByUuid(uuid).orElseGet(this::saveNewCart);
    }

    private Cart saveNewCart() {
        return cartRepository.save(Cart.builder()
                .uuid(UUID.randomUUID().toString())
                .created(LocalDateTime.now())
                .build());
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
