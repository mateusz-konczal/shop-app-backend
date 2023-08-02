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
import static org.mockito.ArgumentMatchers.anyString;
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
    void should_add_product_to_cart_when_cart_uuid_does_not_exist() {
        // GIVEN
        Long productId = 1L;
        Long cartId = 1L;
        String cartUuid = "2f89a7ff-d881-468a-83cd-434d8db49eaf";
        CartItemDto cartItemDto = new CartItemDto(productId, 1);
        given(cartRepository.save(any(Cart.class))).willReturn(Cart.builder().id(cartId).uuid(cartUuid).build());
        given(productRepository.findById(anyLong())).willReturn(Optional.of(Product.builder().id(productId).build()));
        // WHEN
        Cart result = cartService.addProductToCart(null, cartItemDto);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(cartId);
        assertThat(result.getUuid()).isEqualTo(cartUuid);
    }

    @Test
    void should_add_product_to_cart_when_cart_uuid_exists() {
        // GIVEN
        Long productId = 1L;
        Long cartId = 2L;
        String cartUuid = "c71ea366-c37e-493e-ad5a-e41d9fc74619";
        CartItemDto cartItemDto = new CartItemDto(productId, 1);
        given(cartRepository.findByUuid(anyString())).willReturn(Optional.of(Cart.builder().id(cartId).uuid(cartUuid).build()));
        given(productRepository.findById(anyLong())).willReturn(Optional.of(Product.builder().id(productId).build()));
        // WHEN
        Cart result = cartService.addProductToCart(cartUuid, cartItemDto);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(cartId);
        assertThat(result.getUuid()).isEqualTo(cartUuid);
    }

    @Test
    void should_update_cart_when_product_quantity_is_changed() {
        // GIVEN
        String cartUuid = "0f1e5e53-c705-420a-a559-6e9a399a015b";
        List<CartItemDto> itemDtoList = new ArrayList<>();
        itemDtoList.add(new CartItemDto(1L, 3));
        given(cartRepository.findByUuid(anyString())).willReturn(Optional.of(getCart()));
        // WHEN
        Cart result = cartService.updateCart(cartUuid, itemDtoList);
        // THEN
        assertThat(result.getItems()).hasSize(1);
        assertThat(result.getItems().get(0).getQuantity()).isEqualTo(3);
    }

    private Cart getCart() {
        Long cartId = 3L;
        String cartUuid = "0f1e5e53-c705-420a-a559-6e9a399a015b";
        List<CartItem> items = new ArrayList<>();
        items.add(CartItem.builder()
                .quantity(2)
                .product(Product.builder().id(1L).build())
                .cartId(cartId)
                .build());

        return Cart.builder()
                .id(cartId)
                .uuid(cartUuid)
                .items(items)
                .build();
    }
}
