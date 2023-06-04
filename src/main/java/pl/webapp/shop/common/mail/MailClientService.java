package pl.webapp.shop.common.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailClientService {

    private final Map<String, MailSender> mailSenderMap;

    @Value("${app.mail.sender.isFakeMailService}")
    private boolean isFakeMailService;

    public MailSender getInstance() {
        if (isFakeMailService) {
            return mailSenderMap.get("fakeMailService");
        }

        return mailSenderMap.get("simpleMailService");
    }

    public MailSender getInstance(String beanName) {
        return mailSenderMap.get(beanName);
    }
}
