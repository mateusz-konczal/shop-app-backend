package pl.webapp.shop.security.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordDto(

        @NotBlank
        String hash,

        @NotBlank
        String password,

        @NotBlank
        String repeatedPassword) {
}
