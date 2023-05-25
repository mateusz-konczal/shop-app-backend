package pl.webapp.shop.admin.user.controller.dto;

import lombok.Builder;
import pl.webapp.shop.security.model.UserRole;

import java.util.List;

@Builder
public record AdminUserReadDto(

        Long id,
        String username,
        boolean enabled,
        List<UserRole> userRoles) {
}
