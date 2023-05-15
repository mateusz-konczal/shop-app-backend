package pl.webapp.shop.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.security.dto.EmailDto;
import pl.webapp.shop.security.service.LostPasswordService;

@RestController
@RequestMapping("/api/v1/lostPassword")
@RequiredArgsConstructor
class LostPasswordController {

    private final LostPasswordService lostPasswordService;

    @PostMapping
    void resetPassword(@RequestBody @Valid EmailDto emailDto) {
        lostPasswordService.sendLostPasswordLink(emailDto);
    }
}
