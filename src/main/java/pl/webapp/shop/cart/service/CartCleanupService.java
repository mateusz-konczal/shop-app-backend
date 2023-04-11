package pl.webapp.shop.cart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.webapp.shop.cart.model.Cart;
import pl.webapp.shop.cart.repository.CartRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
class CartCleanupService {

    private final CartRepository cartRepository;

    @Scheduled(cron = "${app.cart.cleanup.expression}")
    void cleanupOldCarts() {
        List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
        log.info("Number of old carts: " + carts.size());
    }
}
