package pl.webapp.shop.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.security.dto.EmailDto;
import pl.webapp.shop.security.dto.ResetPasswordDto;
import pl.webapp.shop.security.service.LostPasswordService;

@RestController
@RequiredArgsConstructor
class LostPasswordController {

    private final LostPasswordService lostPasswordService;

    @PostMapping("/lostPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void sendLostPasswordLink(@RequestBody @Valid EmailDto emailDto) {
        lostPasswordService.sendLostPasswordLink(emailDto);
    }

    @PostMapping("/resetPassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void changePassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        lostPasswordService.changePassword(resetPasswordDto);
    }
}
