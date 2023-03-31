package pl.webapp.shop.review.controller.mapper;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import pl.webapp.shop.review.controller.dto.ReviewDto;
import pl.webapp.shop.review.model.Review;

public class ReviewMapper {

    private ReviewMapper() {
    }

    public static Review mapToReview(ReviewDto reviewDto) {
        return Review.builder()
                .productId(reviewDto.productId())
                .authorName(clearText(reviewDto.authorName()))
                .content(clearText(reviewDto.content()))
                .build();
    }

    private static String clearText(String text) {
        return Jsoup.clean(text, Safelist.none());
    }
}
