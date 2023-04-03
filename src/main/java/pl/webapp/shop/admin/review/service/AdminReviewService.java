package pl.webapp.shop.admin.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.admin.review.model.AdminReview;
import pl.webapp.shop.admin.review.repository.AdminReviewRepository;

@Service
@RequiredArgsConstructor
public class AdminReviewService {

    private final AdminReviewRepository reviewRepository;

    public Page<AdminReview> getReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Transactional
    public void moderateReview(Long id) {
        reviewRepository.moderateById(id);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
