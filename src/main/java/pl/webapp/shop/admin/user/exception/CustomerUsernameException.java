package pl.webapp.shop.admin.user.exception;

import pl.webapp.shop.common.exception.BusinessException;

public class CustomerUsernameException extends BusinessException {

    public CustomerUsernameException() {
        super("Nazwą użytkownika konta klienta musi być poprawny adres e-mail");
    }
}
