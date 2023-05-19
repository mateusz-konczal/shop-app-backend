package pl.webapp.shop.admin.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.user.dto.AdminNewPasswordDto;
import pl.webapp.shop.admin.user.service.AdminAccountService;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
class AdminAccountController {

    private final AdminAccountService accountService;

    @PostMapping("/newPassword")
    void changePassword(@RequestBody @Valid AdminNewPasswordDto adminNewPasswordDto, @AuthenticationPrincipal String uuid) {
        accountService.changePassword(adminNewPasswordDto, uuid);
    }

    @DeleteMapping("/deleteAccount")
    void deleteAccount(@AuthenticationPrincipal String uuid) {
        accountService.deleteAccount(uuid);
    }
}
