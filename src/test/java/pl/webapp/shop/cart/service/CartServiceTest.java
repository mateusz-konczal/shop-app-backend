package pl.webapp.shop.cart.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.webapp.shop.cart.dto.CartItemDto;
import pl.webapp.shop.common.model.Cart;
import pl.webapp.shop.common.model.CartItem;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.repository.CartRepository;
import pl.webapp.shop.common.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private CartService cartService;

    @Test
    void shouldAddProductToCartWhenCartIdDoesNotExist() {
        // GIVEN
        Long cartId = 0L;
        Long productId = 1L;
        CartItemDto cartItemDto = new CartItemDto(productId, 1);
        given(cartRepository.save(any(Cart.class))).willReturn(Cart.builder().id(1L).build());
        given(productRepository.findById(anyLong())).willReturn(Optional.of(Product.builder().id(productId).build()));
        // WHEN
        Cart result = cartService.addProductToCart(cartId, cartItemDto);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void shouldAddProductToCartWhenCartIdExists() {
        // GIVEN
        Long cartId = 1L;
        Long productId = 1L;
        CartItemDto cartItemDto = new CartItemDto(productId, 1);
        given(cartRepository.findById(anyLong())).willReturn(Optional.of(Cart.builder().id(cartId).build()));
        given(productRepository.findById(anyLong())).willReturn(Optional.of(Product.builder().id(productId).build()));
        // WHEN
        Cart result = cartService.addProductToCart(cartId, cartItemDto);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(cartId);
    }

    @Test
    void shouldUpdateCartWhenProductQuantityIsChanged() {
        // GIVEN
        Long cartId = 1L;
        List<CartItemDto> itemDtoList = new ArrayList<>();
        itemDtoList.add(new CartItemDto(1L, 3));
        given(cartRepository.findById(anyLong())).willReturn(Optional.of(getCart()));
        // WHEN
        Cart result = cartService.updateCart(cartId, itemDtoList);
        // THEN
        assertThat(result.getItems()).hasSize(1);
        assertThat(result.getItems().get(0).getQuantity()).isEqualTo(3);
    }

    private Cart getCart() {
        Long cartId = 1L;
        List<CartItem> items = new ArrayList<>();
        items.add(CartItem.builder()
                .quantity(2)
                .product(Product.builder().id(1L).build())
                .cartId(cartId)
                .build());

        return Cart.builder()
                .id(cartId)
                .items(items)
                .build();
    }
}
