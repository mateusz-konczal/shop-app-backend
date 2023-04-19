package pl.webapp.shop.common.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class FakeMailService implements MailSender {

    @Override
    public void send(String to, String subject, String content) {
        log.info("Send an e-mail");
        log.info("To: " + to);
        log.info("Subject: " + subject);
        log.info("Content: " + content);
    }
}
