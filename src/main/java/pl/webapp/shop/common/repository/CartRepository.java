package pl.webapp.shop.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.common.model.Cart;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUuid(String uuid);

    @Query("""
            SELECT c FROM Cart c
            LEFT JOIN FETCH c.items ci
            GROUP BY ci.cartId
            HAVING MAX(ci.created) < :minusDays
            OR (ci.cartId IS NULL AND c.created < :minusDays)
            """)
    List<Cart> findAllByCreatedCartItemLessThan(LocalDateTime minusDays);

    @Query("DELETE FROM Cart c WHERE c.id IN (:ids)")
    @Modifying
    void deleteAllByIdIn(List<Long> ids);

    @Query("DELETE FROM Cart c WHERE c.id = :id")
    @Modifying
    void deleteCartById(Long id);
}
