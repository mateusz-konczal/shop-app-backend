package pl.webapp.shop.common.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    @Test
    void shouldIncrementQuantityWhenProductIsAlreadyInCart() {
        // GIVEN
        Long cartId = 1L;
        Cart cart = Cart.builder()
                .id(cartId)
                .uuid("d24d3cd0-6d0c-4524-8a23-d92c02e51827")
                .items(getItems())
                .build();
        CartItem cartItem = CartItem.builder()
                .quantity(1)
                .product(Product.builder().id(1L).build())
                .cartId(cartId)
                .build();
        // WHEN
        cart.addProduct(cartItem);
        // THEN
        assertThat(cart.getItems()).hasSize(1);
        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(2);
    }

    @Test
    void shouldAddProductToCartWhenProductDoesNotExist() {
        // GIVEN
        Long cartId = 1L;
        Cart cart = Cart.builder()
                .id(cartId)
                .uuid("a387a2ef-41a6-411e-8329-8dbba2f0bf05")
                .items(getItems())
                .build();
        CartItem cartItem = CartItem.builder()
                .quantity(1)
                .product(Product.builder().id(2L).build())
                .cartId(cartId)
                .build();
        // WHEN
        cart.addProduct(cartItem);
        // THEN
        assertThat(cart.getItems()).hasSize(2);
        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(1);
        assertThat(cart.getItems().get(1).getQuantity()).isEqualTo(1);
    }

    private List<CartItem> getItems() {
        List<CartItem> items = new ArrayList<>();
        items.add(CartItem.builder()
                .quantity(1)
                .product(Product.builder().id(1L).build())
                .cartId(1L)
                .build());

        return items;
    }
}
