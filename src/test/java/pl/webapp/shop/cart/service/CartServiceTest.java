package pl.webapp.shop.cart.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.webapp.shop.cart.dto.CartProductDto;
import pl.webapp.shop.cart.model.Cart;
import pl.webapp.shop.cart.repository.CartRepository;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.repository.ProductRepository;

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
        CartProductDto cartProductDto = new CartProductDto(productId, 1);
        given(productRepository.findById(anyLong())).willReturn(Optional.of(Product.builder().id(productId).build()));
        given(cartRepository.save(any(Cart.class))).willReturn(Cart.builder().id(1L).build());
        // WHEN
        Cart result = cartService.addProductToCart(cartId, cartProductDto);
        // GIVEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }
}