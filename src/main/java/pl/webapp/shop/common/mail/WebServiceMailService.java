package pl.webapp.shop.common.mail;

import org.springframework.stereotype.Service;
import pl.webapp.shop.common.exception.NoImplementationException;

@Service
class WebServiceMailService implements MailSender {

    @Override
    public void send(String to, String subject, String content) {
        throw new NoImplementationException();
    }
}
