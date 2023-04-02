package pl.webapp.shop.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.shop.common.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
