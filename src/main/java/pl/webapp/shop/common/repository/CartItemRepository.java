package pl.webapp.shop.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.common.model.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Long countByCartId(Long cartId);

    @Query("DELETE FROM CartItem ci WHERE ci.cartId IN (:cartIds)")
    @Modifying
    void deleteAllByCartIdIn(List<Long> cartIds);

    @Query("DELETE FROM CartItem ci WHERE ci.cartId = :cartId")
    @Modifying
    void deleteAllByCartId(Long cartId);
}
