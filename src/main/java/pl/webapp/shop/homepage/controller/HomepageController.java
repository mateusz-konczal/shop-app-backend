package pl.webapp.shop.homepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.homepage.controller.dto.HomepageDto;
import pl.webapp.shop.homepage.service.HomepageService;

@RestController
@RequestMapping("/homepage")
@RequiredArgsConstructor
class HomepageController {

    private final HomepageService homepageService;

    @GetMapping
    HomepageDto getHomepage() {
        return new HomepageDto(homepageService.getSaleProducts());
    }
}
