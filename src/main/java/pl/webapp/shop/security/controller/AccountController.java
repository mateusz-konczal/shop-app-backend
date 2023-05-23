package pl.webapp.shop.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.security.dto.NewPasswordDto;
import pl.webapp.shop.security.service.AccountService;

@RestController
@RequiredArgsConstructor
class AccountController {

    private final AccountService accountService;

    @PostMapping("/newPassword")
    void changePassword(@RequestBody @Valid NewPasswordDto newPasswordDto, @AuthenticationPrincipal String uuid) {
        accountService.changePassword(newPasswordDto, uuid);
    }

    @DeleteMapping("/deleteAccount")
    void deleteAccount(@AuthenticationPrincipal String uuid) {
        accountService.deleteAccount(uuid);
    }
}
