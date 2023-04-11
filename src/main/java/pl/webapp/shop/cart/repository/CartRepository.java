package pl.webapp.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.cart.model.Cart;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByCreatedLessThan(LocalDateTime minusDays);
}
