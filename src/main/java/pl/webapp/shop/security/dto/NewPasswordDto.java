package pl.webapp.shop.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static pl.webapp.shop.ShopApplication.PASSWORD_REGEX;

public record NewPasswordDto(

        @Pattern(regexp = PASSWORD_REGEX)
        String password,

        @NotBlank
        String repeatedPassword) {
}
