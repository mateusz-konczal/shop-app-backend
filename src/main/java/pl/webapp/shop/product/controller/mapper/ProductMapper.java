package pl.webapp.shop.product.controller.mapper;

import org.springframework.data.domain.Page;
import pl.webapp.shop.product.controller.dto.ProductReadDto;
import pl.webapp.shop.product.model.Product;

import java.util.List;

public class ProductMapper {

    private ProductMapper() {
    }

    public static List<ProductReadDto> mapToProductReadDtoList(Page<Product> products) {
        return products.getContent().stream()
                .map(ProductMapper::mapToProductReadDto)
                .toList();
    }

    private static ProductReadDto mapToProductReadDto(Product product) {
        return ProductReadDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .build();
    }
}
