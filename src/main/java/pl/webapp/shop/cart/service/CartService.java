package pl.webapp.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.cart.dto.CartItemDto;
import pl.webapp.shop.cart.model.Cart;
import pl.webapp.shop.cart.model.CartItem;
import pl.webapp.shop.cart.repository.CartRepository;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.repository.ProductRepository;

import java.util.List;

import static java.time.LocalDateTime.now;

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
                .created(now())
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
            return cartRepository.save(Cart.builder().created(now()).build());
        }

        return cartRepository.findById(id).orElseThrow();
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
