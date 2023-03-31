package pl.webapp.shop.admin.category.controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AdminCategoryDto(

        @NotBlank
        @Length(min = 4)
        String name,

        String description,

        @NotBlank
        @Length(min = 4)
        String slug) {
}
