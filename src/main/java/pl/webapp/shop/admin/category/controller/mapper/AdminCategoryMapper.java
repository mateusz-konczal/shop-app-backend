package pl.webapp.shop.admin.category.controller.mapper;

import pl.webapp.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.webapp.shop.admin.category.model.AdminCategory;

import static pl.webapp.shop.admin.common.utils.SlugifyUtils.slugifySlug;

public class AdminCategoryMapper {

    private AdminCategoryMapper() {
    }

    public static AdminCategory mapToAdminCategory(AdminCategoryDto adminCategoryDto, Long id) {
        return AdminCategory.builder()
                .id(id)
                .name(adminCategoryDto.name())
                .description(adminCategoryDto.description())
                .slug(slugifySlug(adminCategoryDto.slug()))
                .build();
    }
}
