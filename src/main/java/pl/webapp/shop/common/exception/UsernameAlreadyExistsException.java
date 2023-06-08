package pl.webapp.shop.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public  class UsernameAlreadyExistsException extends BusinessException {

    public UsernameAlreadyExistsException() {
        super("Podana nazwa użytkownika już istnieje");
    }
}
