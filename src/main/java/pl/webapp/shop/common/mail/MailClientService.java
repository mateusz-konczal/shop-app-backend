package pl.webapp.shop.common.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailClientService {

    @Value("${app.mail.sender.isFakeMailService}")
    private boolean isFakeMailService;
    private final Map<String, MailSender> mailSenderMap;

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
