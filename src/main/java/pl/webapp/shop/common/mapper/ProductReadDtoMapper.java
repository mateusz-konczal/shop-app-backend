package pl.webapp.shop.common.mapper;

import org.springframework.data.domain.Page;
import pl.webapp.shop.common.dto.ProductReadDto;
import pl.webapp.shop.common.model.Product;

import java.util.List;

public class ProductReadDtoMapper {

    private ProductReadDtoMapper() {
    }

    public static List<ProductReadDto> mapToProductReadDtoList(Page<Product> products) {
        return products.getContent().stream()
                .map(ProductReadDtoMapper::mapToProductReadDto)
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
