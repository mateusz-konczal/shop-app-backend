package pl.webapp.shop.common.mail;

import org.springframework.stereotype.Service;

@Service
class WebServiceMailService implements MailSender {

    @Override
    public void send(String to, String subject, String content) {
        throw new RuntimeException("Not implemented yet!");
    }
}
