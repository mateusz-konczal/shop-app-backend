package pl.webapp.shop.review.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ReviewDto(

        @NotNull
        Long productId,

        @NotBlank
        @Length(min = 2, max = 60)
        String authorName,

        @NotBlank
        @Length(min = 4, max = 600)
        String content) {
}
