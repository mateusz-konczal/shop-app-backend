package pl.webapp.shop.admin.product.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import pl.webapp.shop.common.model.ProductCurrency;

import java.math.BigDecimal;

public record AdminProductDto(

        @NotBlank
        @Length(min = 4)
        String name,

        @NotNull
        Long categoryId,

        @NotBlank
        @Length(min = 4)
        String description,

        String fullDescription,

        @NotNull
        @Min(0)
        BigDecimal price,

        ProductCurrency currency,

        String image,

        @NotBlank
        @Length(min = 4)
        String slug) {
}
