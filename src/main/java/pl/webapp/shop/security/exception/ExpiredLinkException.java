package pl.webapp.shop.security.exception;

import pl.webapp.shop.common.exception.BusinessException;

public class ExpiredLinkException extends BusinessException {

    public ExpiredLinkException() {
        super("Link do zmiany hasła stracił ważność");
    }
}
