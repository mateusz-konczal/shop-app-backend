package pl.webapp.shop.admin.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.admin.common.model.AdminProduct;
import pl.webapp.shop.admin.common.repository.AdminProductRepository;
import pl.webapp.shop.admin.common.repository.AdminReviewRepository;
import pl.webapp.shop.admin.common.service.AdminProductCachingService;
import pl.webapp.shop.admin.review.model.AdminReview;

@Service
@RequiredArgsConstructor
public class AdminReviewService {

    private final AdminReviewRepository reviewRepository;
    private final AdminProductRepository productRepository;
    private final AdminProductCachingService productCachingService;

    public Page<AdminReview> getReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Transactional
    public void moderateReview(Long id) {
        AdminReview review = reviewRepository.findById(id).orElseThrow();
        clearProductDetailsCache(review.getProductId());
        reviewRepository.moderateById(id);
    }

    public void deleteReview(Long id) {
        AdminReview review = reviewRepository.findById(id).orElseThrow();
        if (review.isModerated()) {
            clearProductDetailsCache(review.getProductId());
        }
        reviewRepository.deleteById(id);
    }

    private void clearProductDetailsCache(Long productId) {
        AdminProduct product = productRepository.findById(productId).orElseThrow();
        productCachingService.clearProductDetailsCache(product);
    }
}
