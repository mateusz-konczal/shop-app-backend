package pl.webapp.shop.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public abstract class BusinessException extends RuntimeException {

    protected BusinessException(String message) {
        super(message);
    }
}
