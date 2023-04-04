package pl.webapp.shop.admin.product.controller.mapper;

import pl.webapp.shop.admin.product.controller.dto.AdminProductDto;
import pl.webapp.shop.admin.product.model.AdminProduct;

import static pl.webapp.shop.admin.common.utils.SlugifyUtils.slugifySlug;

public class AdminProductMapper {

    private AdminProductMapper() {
    }

    public static AdminProduct mapToAdminProduct(AdminProductDto adminProductDto, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProductDto.name())
                .categoryId(adminProductDto.categoryId())
                .description(adminProductDto.description())
                .fullDescription(adminProductDto.fullDescription())
                .price(adminProductDto.price())
                .currency(adminProductDto.currency())
                .image(adminProductDto.image())
                .slug(slugifySlug(adminProductDto.slug()))
                .build();
    }
}
