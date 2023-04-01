package pl.webapp.shop.product.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.product.controller.dto.ProductReadDto;
import pl.webapp.shop.product.model.Product;
import pl.webapp.shop.product.service.ProductService;

import java.util.List;

import static pl.webapp.shop.product.controller.mapper.ProductMapper.mapToProductReadDtoList;

@RestController
@RequestMapping("/api/v1/products")
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
    Product getProduct(@PathVariable
                       @Pattern(regexp = "[a-z0-9\\-]+")
                       @Length(max = 255) String slug) {
        return productService.getProduct(slug);
    }
}
