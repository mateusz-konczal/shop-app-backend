package pl.webapp.shop.category.model.dto;

import org.springframework.data.domain.Page;
import pl.webapp.shop.category.model.Category;
import pl.webapp.shop.product.model.Product;

public record CategoryProductsDto(
        Category category,
        Page<Product> products) {
}
