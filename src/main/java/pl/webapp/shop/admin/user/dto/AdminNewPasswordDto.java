package pl.webapp.shop.admin.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static pl.webapp.shop.ShopApplication.PASSWORD_REGEX;

public record AdminNewPasswordDto(

        @Pattern(regexp = PASSWORD_REGEX)
        String password,

        @NotBlank
        String repeatedPassword) {
}
