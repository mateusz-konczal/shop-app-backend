package pl.webapp.shop.contact.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.webapp.shop.contact.dto.ContactDto;

@Service
public class ContactService {

    @Value("${app.mail.sender.address}")
    private String senderAddress;

    public ContactDto getContact() {
        return new ContactDto(senderAddress);
    }
}
