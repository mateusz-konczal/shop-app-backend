package pl.webapp.shop.product.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.model.Review;
import pl.webapp.shop.common.repository.ProductRepository;
import pl.webapp.shop.common.repository.ReviewRepository;
import pl.webapp.shop.product.dto.ProductReviewsDto;

import java.util.ArrayList;
import java.util.List;

import static pl.webapp.shop.common.mapper.ProductReviewsMapper.mapToProductReviewsDto;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public Page<Product> getProducts(Pageable pageable) {
        return getProductPage(pageable);
    }

    public Page<Product> getProductsByPhrase(String phrase, Pageable pageable) {
        return productRepository.findAll(getProductSpecification(phrase), pageable);
    }

    @Transactional(readOnly = true)
    public ProductReviewsDto getProduct(String slug) {
        Product product = productRepository.findBySlug(slug).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProductIdAndModeratedIsTrueOrderByIdDesc(product.getId());

        return mapToProductReviewsDto(product, reviews);
    }

    private Page<Product> getProductPage(Pageable pageable) {
        String sort = pageable.getSort().toString().toLowerCase();
        return switch (sort) {
            case "price: asc" -> productRepository.findAllOrderByEndPriceAsc(PageRequest.of(
                    pageable.getPageNumber(), pageable.getPageSize()));
            case "price: desc" -> productRepository.findAllOrderByEndPriceDesc(PageRequest.of(
                    pageable.getPageNumber(), pageable.getPageSize()));
            default -> productRepository.findAllByEnabledIsTrue(pageable);
        };
    }

    private static Specification<Product> getProductSpecification(String phrase) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.and(builder.equal(root.get("enabled"), true)));

            if (StringUtils.hasText(phrase)) {
                String pattern = "%" + phrase.toLowerCase() + "%";
                Predicate productName = builder.like(builder.lower(root.get("name")), pattern);
                Predicate productDescription = builder.like(builder.lower(root.get("description")), pattern);
                predicates.add(builder.or(productName, productDescription));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
