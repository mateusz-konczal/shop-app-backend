package pl.webapp.shop.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.webapp.shop.common.exception.BusinessException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends BusinessException {

    public EmailNotFoundException() {
        super("Podany adres e-mail nie istnieje");
    }
}
