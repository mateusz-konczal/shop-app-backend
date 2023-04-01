package pl.webapp.shop.category.model.dto;

import org.springframework.data.domain.Page;
import pl.webapp.shop.category.model.Category;
import pl.webapp.shop.product.controller.dto.ProductReadDto;

public record CategoryProductsDto(
        Category category,
        Page<ProductReadDto> products) {
}
