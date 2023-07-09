package pl.webapp.shop.common.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carts")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private LocalDateTime created;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cartId")
    private List<CartItem> items;

    public void addProduct(CartItem cartItem) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.stream()
                .filter(item -> Objects.equals(cartItem.getProduct().getId(), item.getProduct().getId()))
                .findFirst()
                .ifPresentOrElse(
                        item -> {
                            item.setCreated(LocalDateTime.now());
                            item.setQuantity(item.getQuantity() + 1);
                        },
                        () -> items.add(cartItem)
                );
    }
}
