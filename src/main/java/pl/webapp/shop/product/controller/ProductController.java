package pl.webapp.shop.product.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.common.dto.ProductReadDto;
import pl.webapp.shop.common.model.Product;
import pl.webapp.shop.product.service.ProductService;
import pl.webapp.shop.product.service.dto.ProductReviewsDto;

import java.util.List;

import static pl.webapp.shop.common.mapper.ProductReadDtoMapper.mapToProductReadDtoList;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
class ProductController {

    private final ProductService productService;

    @GetMapping
    Page<ProductReadDto> getProducts(Pageable pageable) {
        Page<Product> products = productService.getProducts(pageable);
        List<ProductReadDto> productReadDtoList = mapToProductReadDtoList(products);

        return new PageImpl<>(productReadDtoList, pageable, products.getTotalElements());
    }

    @GetMapping("/{slug}")
    @Cacheable("productDetails")
    public ProductReviewsDto getProduct(@PathVariable
                                 @Pattern(regexp = "[a-z0-9\\-]+")
                                 @Length(max = 255) String slug) {
        return productService.getProduct(slug);
    }
}
