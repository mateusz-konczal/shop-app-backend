package pl.webapp.shop.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.common.model.Review;
import pl.webapp.shop.review.controller.dto.ReviewDto;
import pl.webapp.shop.review.service.ReviewService;

import static pl.webapp.shop.review.controller.mapper.ReviewMapper.mapToReview;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    Review createReview(@RequestBody @Valid ReviewDto reviewDto, @AuthenticationPrincipal String userUuid) {
        return reviewService.createReview(mapToReview(reviewDto, userUuid));
    }
}
