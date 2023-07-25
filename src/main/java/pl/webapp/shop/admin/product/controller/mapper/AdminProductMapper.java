package pl.webapp.shop.admin.product.controller.mapper;

import pl.webapp.shop.admin.common.model.AdminProduct;
import pl.webapp.shop.admin.product.controller.dto.AdminProductDto;

import static pl.webapp.shop.admin.common.utils.SlugifyUtils.slugifySlug;

public final class AdminProductMapper {

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
                .salePrice(adminProductDto.salePrice())
                .currency(adminProductDto.currency())
                .image(adminProductDto.image())
                .slug(slugifySlug(adminProductDto.slug()))
                .enabled(adminProductDto.enabled())
                .build();
    }
}
