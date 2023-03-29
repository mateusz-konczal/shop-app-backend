package pl.webapp.shop.admin.product.controller.mapper;

import com.github.slugify.Slugify;
import pl.webapp.shop.admin.product.controller.dto.AdminProductDto;
import pl.webapp.shop.admin.product.model.AdminProduct;

public class AdminProductMapper {

    private AdminProductMapper() {
    }

    public static AdminProduct mapToAdminProduct(AdminProductDto adminProductDto, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProductDto.name())
                .category(adminProductDto.category())
                .description(adminProductDto.description())
                .fullDescription(adminProductDto.fullDescription())
                .price(adminProductDto.price())
                .currency(adminProductDto.currency())
                .image(adminProductDto.image())
                .slug(slugifySlug(adminProductDto.slug()))
                .build();
    }

    private static String slugifySlug(String slug) {
        Slugify slg = Slugify.builder()
                .customReplacement("_", "-")
                .build();

        return slg.slugify(slug);
    }
}
