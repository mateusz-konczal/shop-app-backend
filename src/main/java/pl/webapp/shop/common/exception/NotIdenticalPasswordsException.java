package pl.webapp.shop.common.exception;

public class NotIdenticalPasswordsException extends BusinessException {

    public NotIdenticalPasswordsException() {
        super("Hasła nie są identyczne");
    }
}
