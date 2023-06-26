package pl.webapp.shop.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.webapp.shop.common.dto.ReviewReadDto;
import pl.webapp.shop.common.model.Review;
import pl.webapp.shop.common.repository.ReviewRepository;

import static pl.webapp.shop.common.mapper.ProductReviewsMapper.mapToReviewReadDto;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewReadDto createReview(Review review) {
        return mapToReviewReadDto(reviewRepository.save(review));
    }
}
