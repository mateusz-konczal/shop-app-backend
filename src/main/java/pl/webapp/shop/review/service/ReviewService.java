package pl.webapp.shop.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.review.model.Review;
import pl.webapp.shop.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
}
