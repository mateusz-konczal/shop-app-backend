package pl.webapp.shop.security.dto;

import jakarta.validation.constraints.Email;

public record EmailDto(

        @Email
        String email) {
}
