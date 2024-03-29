package pl.webapp.shop.homepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.homepage.controller.dto.HomepageDto;
import pl.webapp.shop.homepage.service.HomepageService;

@RestController
@RequestMapping("/homepage")
@RequiredArgsConstructor
class HomepageController {

    private final HomepageService homepageService;

    @GetMapping
    @Cacheable("homepage")
    public HomepageDto getHomepage(@RequestParam(required = false) String sort) {
        return new HomepageDto(homepageService.getSaleProducts(sort));
    }
}
