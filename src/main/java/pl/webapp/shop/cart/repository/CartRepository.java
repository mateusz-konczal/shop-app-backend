package pl.webapp.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
