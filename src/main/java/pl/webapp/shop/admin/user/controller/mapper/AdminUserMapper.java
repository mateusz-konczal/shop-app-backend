package pl.webapp.shop.admin.user.controller.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.webapp.shop.admin.user.controller.dto.AdminUserDto;
import pl.webapp.shop.admin.user.controller.dto.AdminUserReadDto;
import pl.webapp.shop.admin.user.model.AdminUser;

import java.util.List;
import java.util.UUID;

public class AdminUserMapper {

    private AdminUserMapper() {
    }

    public static AdminUser mapToAdminUser(AdminUserDto adminUserDto) {
        return AdminUser.builder()
                .uuid(UUID.randomUUID().toString())
                .username(adminUserDto.username())
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode(adminUserDto.password()))
                .enabled(true)
                .authorities(List.of(adminUserDto.userRole()))
                .build();
    }

    public static Page<AdminUserReadDto> mapToAdminUserReadDtoPage(Page<AdminUser> adminUserPage) {
        return new PageImpl<>(mapToAdminUserReadDtoList(adminUserPage.getContent()),
                adminUserPage.getPageable(), adminUserPage.getTotalElements());
    }

    private static List<AdminUserReadDto> mapToAdminUserReadDtoList(List<AdminUser> adminUserList) {
        return adminUserList.stream()
                .map(AdminUserMapper::mapToAdminUserReadDto)
                .toList();
    }

    private static AdminUserReadDto mapToAdminUserReadDto(AdminUser adminUser) {
        return AdminUserReadDto.builder()
                .id(adminUser.getId())
                .username(adminUser.getUsername())
                .enabled(adminUser.isEnabled())
                .userRoles(adminUser.getAuthorities())
                .build();
    }
}
