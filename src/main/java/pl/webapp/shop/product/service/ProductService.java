package pl.webapp.shop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.common.model.Review;
import pl.webapp.shop.common.repository.ProductRepository;
import pl.webapp.shop.common.repository.ReviewRepository;
import pl.webapp.shop.product.dto.ProductReviewsDto;

import java.util.List;

import static pl.webapp.shop.common.mapper.ProductReviewsMapper.mapToProductReviewsDto;
import static pl.webapp.shop.product.service.ProductSpecification.createProductSpecification;
import static pl.webapp.shop.product.service.ProductSpecification.createProductSpecificationForAscEndPrice;
import static pl.webapp.shop.product.service.ProductSpecification.createProductSpecificationForDescEndPrice;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public Page<Product> getProducts(String phrase, Pageable pageable) {
        String sort = pageable.getSort().toString().toLowerCase();
        return switch (sort) {
            case "price: asc" -> productRepository.findAll(createProductSpecificationForAscEndPrice(phrase),
                    PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
            case "price: desc" -> productRepository.findAll(createProductSpecificationForDescEndPrice(phrase),
                    PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
            default -> productRepository.findAll(createProductSpecification(phrase), pageable);
        };
    }

    @Transactional(readOnly = true)
    public ProductReviewsDto getProduct(String slug) {
        Product product = productRepository.findBySlug(slug).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProductIdAndModeratedIsTrueOrderByIdDesc(product.getId());

        return mapToProductReviewsDto(product, reviews);
    }
}
