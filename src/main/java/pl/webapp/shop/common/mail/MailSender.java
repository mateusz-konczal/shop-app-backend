package pl.webapp.shop.common.mail;

public interface MailSender {

    void send(String to, String subject, String content);
}
