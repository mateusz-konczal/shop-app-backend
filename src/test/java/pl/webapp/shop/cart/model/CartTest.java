package pl.webapp.shop.cart.model;

import org.junit.jupiter.api.Test;
import pl.webapp.shop.common.model.Product;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    @Test
    void shouldIncrementQuantityWhenProductIsAlreadyInCart() {
        // GIVEN
        Cart cart = Cart.builder()
                .id(1L)
                .items(getItems())
                .build();
        CartItem cartItem = CartItem.builder()
                .quantity(1)
                .product(Product.builder().id(1L).build())
                .cartId(1L)
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
        Cart cart = Cart.builder()
                .id(1L)
                .items(getItems())
                .build();
        CartItem cartItem = CartItem.builder()
                .quantity(1)
                .product(Product.builder().id(2L).build())
                .cartId(1L)
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