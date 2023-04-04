package pl.webapp.shop.category.service.dto;

import org.springframework.data.domain.Page;
import pl.webapp.shop.common.dto.ProductReadDto;
import pl.webapp.shop.common.model.Category;

public record CategoryProductsDto(

        Category category,
        Page<ProductReadDto> products) {
}
