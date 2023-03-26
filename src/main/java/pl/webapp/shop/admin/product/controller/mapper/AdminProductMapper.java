package pl.webapp.shop.admin.product.controller.mapper;

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
                .price(adminProductDto.price())
                .currency(adminProductDto.currency())
                .build();
    }

}
