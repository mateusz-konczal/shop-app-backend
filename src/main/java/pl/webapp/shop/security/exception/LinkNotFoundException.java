package pl.webapp.shop.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.webapp.shop.common.exception.BusinessException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LinkNotFoundException extends BusinessException {

    public LinkNotFoundException() {
        super("Link do zmiany hasła jest nieprawidłowy");
    }
}
