package pl.webapp.shop.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.webapp.shop.common.exception.BusinessException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LoginException extends BusinessException {

    public LoginException(String message) {
        super(message);
    }
}
