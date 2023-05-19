package pl.webapp.shop.admin.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AdminNewPasswordDto(

        @NotBlank
        String password,

        @NotBlank
        String repeatedPassword) {
}
