package pl.webapp.shop.admin.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.user.controller.dto.AdminUserDto;
import pl.webapp.shop.admin.user.controller.dto.AdminUserReadDto;
import pl.webapp.shop.admin.user.controller.dto.AdminUserRolesDto;
import pl.webapp.shop.admin.user.exception.CustomerUsernameException;
import pl.webapp.shop.admin.user.service.AdminUserService;
import pl.webapp.shop.common.exception.NotIdenticalPasswordsException;
import pl.webapp.shop.common.exception.UsernameAlreadyExistsException;
import pl.webapp.shop.security.model.UserRole;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static pl.webapp.shop.admin.user.controller.mapper.AdminUserMapper.mapToAdminUser;
import static pl.webapp.shop.admin.user.controller.mapper.AdminUserMapper.mapToAdminUserReadDtoPage;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
class AdminUserController {

    private final AdminUserService userService;

    @GetMapping
    Page<AdminUserReadDto> getUsers(Pageable pageable) {
        return mapToAdminUserReadDtoPage(userService.getUsers(pageable));
    }

    @PostMapping
    void createUser(@RequestBody @Valid AdminUserDto adminUserDto) {
        if (!Objects.equals(adminUserDto.password(), adminUserDto.repeatedPassword())) {
            throw new NotIdenticalPasswordsException();
        }
        if (!EmailValidator.getInstance().isValid(adminUserDto.username()) && adminUserDto.userRole() == UserRole.ROLE_CUSTOMER) {
            throw new CustomerUsernameException();
        }
        if (userService.isUserExist(adminUserDto.username())) {
            throw new UsernameAlreadyExistsException();
        }

        userService.createUser(mapToAdminUser(adminUserDto));
    }

    @PutMapping("/{id}/enable")
    void enableUser(@PathVariable Long id) {
        userService.enableUser(id);
    }

    @PutMapping("/{id}/disable")
    void disableUser(@PathVariable Long id) {
        userService.disableUser(id);
    }

    @GetMapping("/initRoles")
    AdminUserRolesDto getUserRoles() {
        return new AdminUserRolesDto(createUserRolesMap());
    }

    private Map<String, String> createUserRolesMap() {
        HashMap<String, String> userRoles = new HashMap<>();
        for (UserRole role : UserRole.values()) {
            userRoles.put(role.name(), role.getRole());
        }

        return userRoles;
    }
}
