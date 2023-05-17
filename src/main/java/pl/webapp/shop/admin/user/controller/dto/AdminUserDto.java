package pl.webapp.shop.admin.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import pl.webapp.shop.security.model.UserRole;

public record AdminUserDto(

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String repeatedPassword,

        UserRole userRole) {
}
