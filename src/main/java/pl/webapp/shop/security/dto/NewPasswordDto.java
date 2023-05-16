package pl.webapp.shop.security.dto;

import jakarta.validation.constraints.NotBlank;

public record NewPasswordDto(

        @NotBlank
        String hash,

        @NotBlank
        String password,

        @NotBlank
        String repeatedPassword) {
}
