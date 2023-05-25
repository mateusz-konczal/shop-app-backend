package pl.webapp.shop.admin.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.review.model.AdminReview;
import pl.webapp.shop.admin.review.service.AdminReviewService;

@RestController
@RequestMapping("/admin/reviews")
@RequiredArgsConstructor
class AdminReviewController {

    private final AdminReviewService reviewService;

    @GetMapping
    Page<AdminReview> getReviews(Pageable pageable) {
        return reviewService.getReviews(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").descending()));
    }

    @PutMapping("/{id}/moderate")
    void moderateReview(@PathVariable Long id) {
        reviewService.moderateReview(id);
    }

    @DeleteMapping("/{id}")
    void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
