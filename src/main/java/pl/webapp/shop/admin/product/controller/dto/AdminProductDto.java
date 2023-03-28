package pl.webapp.shop.admin.product.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import pl.webapp.shop.admin.product.model.AdminProductCurrency;

import java.math.BigDecimal;

public record AdminProductDto(

        @NotBlank
        @Length(min = 4)
        String name,

        @NotBlank
        @Length(min = 4)
        String category,

        @NotBlank
        @Length(min = 4)
        String description,

        @NotNull
        @Min(0)
        BigDecimal price,

        AdminProductCurrency currency,

        String image) {
}
