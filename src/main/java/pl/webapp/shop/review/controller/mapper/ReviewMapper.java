package pl.webapp.shop.review.controller.mapper;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import pl.webapp.shop.common.model.Review;
import pl.webapp.shop.review.controller.dto.ReviewDto;

public final class ReviewMapper {

    private ReviewMapper() {
    }

    public static Review mapToReview(ReviewDto reviewDto, String userUuid) {
        return Review.builder()
                .productId(reviewDto.productId())
                .authorName(clearText(reviewDto.authorName()))
                .content(clearText(reviewDto.content()))
                .userUuid(userUuid)
                .build();
    }

    private static String clearText(String text) {
        return Jsoup.clean(text, Safelist.none());
    }
}
