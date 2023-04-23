package pl.webapp.shop.common.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class SimpleMailService implements MailSender {

    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void send(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("Shop <sklep@mateuszkonczal.pl>");
        mailMessage.setReplyTo("Shop <sklep@mateuszkonczal.pl>");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        javaMailSender.send(mailMessage);
        log.info("E-mail has been sent");
    }
}
