package pl.webapp.shop.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.common.dto.ReviewReadDto;
import pl.webapp.shop.review.controller.dto.ReviewDto;
import pl.webapp.shop.review.service.ReviewService;

import static pl.webapp.shop.review.controller.mapper.ReviewMapper.mapToReview;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ReviewReadDto createReview(@RequestBody @Valid ReviewDto reviewDto, @AuthenticationPrincipal String userUuid) {
        return reviewService.createReview(mapToReview(reviewDto, userUuid));
    }
}
