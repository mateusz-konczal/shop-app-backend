package pl.webapp.shop.order.service.payment.p24.exception;

public class InvalidValidationException extends RuntimeException {

    public InvalidValidationException() {
        super("Nieprawidłowa walidacja wyniku transakcji");
    }
}
