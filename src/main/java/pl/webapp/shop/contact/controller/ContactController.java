package pl.webapp.shop.contact.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.webapp.shop.contact.dto.ContactDto;
import pl.webapp.shop.contact.service.ContactService;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
class ContactController {

    private final ContactService contactService;

    @GetMapping
    ContactDto getContact() {
        return contactService.getContact();
    }
}
