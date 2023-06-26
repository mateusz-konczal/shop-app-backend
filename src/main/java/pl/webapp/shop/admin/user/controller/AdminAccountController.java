package pl.webapp.shop.admin.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.admin.user.dto.AdminNewPasswordDto;
import pl.webapp.shop.admin.user.service.AdminAccountService;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
class AdminAccountController {

    private final AdminAccountService accountService;

    @PostMapping("/newPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void changePassword(@RequestBody @Valid AdminNewPasswordDto adminNewPasswordDto, @AuthenticationPrincipal String uuid) {
        accountService.changePassword(adminNewPasswordDto, uuid);
    }

    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAccount(@AuthenticationPrincipal String uuid) {
        accountService.deleteAccount(uuid);
    }
}
