package pl.webapp.shop.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.security.dto.NewPasswordDto;
import pl.webapp.shop.security.service.AccountService;

@RestController
@RequiredArgsConstructor
class AccountController {

    private final AccountService accountService;

    @PostMapping("/newPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void changePassword(@RequestBody @Valid NewPasswordDto newPasswordDto, @AuthenticationPrincipal String uuid) {
        accountService.changePassword(newPasswordDto, uuid);
    }

    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAccount(@AuthenticationPrincipal String uuid) {
        accountService.deleteAccount(uuid);
    }
}
