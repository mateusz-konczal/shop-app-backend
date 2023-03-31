package pl.webapp.shop.admin.category.controller.mapper;

import com.github.slugify.Slugify;
import pl.webapp.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.webapp.shop.admin.category.model.AdminCategory;

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

    private static String slugifySlug(String slug) {
        Slugify slg = Slugify.builder()
                .customReplacement("_", "-")
                .build();

        return slg.slugify(slug);
    }
}
