package pl.webapp.shop.product.service.mapper;

import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.model.Review;
import pl.webapp.shop.product.service.dto.ProductReviewsDto;
import pl.webapp.shop.product.service.dto.ReviewReadDto;

import java.util.List;

public class ProductReviewsMapper {

    private ProductReviewsMapper() {
    }

    public static ProductReviewsDto mapToProductReviewsDto(Product product, List<Review> reviews) {
        return ProductReviewsDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .reviews(reviews.stream()
                        .map(ProductReviewsMapper::mapToReviewReadDto)
                        .toList())
                .build();
    }

    private static ReviewReadDto mapToReviewReadDto(Review review) {
        return ReviewReadDto.builder()
                .id(review.getId())
                .productId(review.getProductId())
                .authorName(review.getAuthorName())
                .content(review.getContent())
                .moderated(review.isModerated())
                .build();
    }
}
