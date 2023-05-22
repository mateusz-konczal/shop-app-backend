package pl.webapp.shop.admin.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import pl.webapp.shop.security.model.UserRole;

import static pl.webapp.shop.ShopApplication.PASSWORD_REGEX;

public record AdminUserDto(

        @NotBlank
        String username,

        @Pattern(regexp = PASSWORD_REGEX)
        String password,

        @NotBlank
        String repeatedPassword,

        UserRole userRole) {
}
