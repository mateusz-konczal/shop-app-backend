package pl.webapp.shop.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.cart.model.Cart;
import pl.webapp.shop.cart.repository.CartItemRepository;
import pl.webapp.shop.cart.repository.CartRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
class CartCleanupService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    @Scheduled(cron = "${app.cart.cleanup.expression}")
    public void cleanupOldCarts() {
        List<Cart> carts = cartRepository.findByCreatedCartItemLessThan(LocalDateTime.now().minusDays(4));
        List<Long> cartIds = carts.stream()
                .map(Cart::getId)
                .toList();
        log.info("Number of old carts: " + carts.size());

        if (!cartIds.isEmpty()) {
            cartItemRepository.deleteAllByCartIdIn(cartIds);
            cartRepository.deleteAllByIdIn(cartIds);
        }
    }
}
