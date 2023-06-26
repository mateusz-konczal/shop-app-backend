package pl.webapp.shop.common.exception;

public class NoImplementationException extends RuntimeException {

    public NoImplementationException() {
        super("Jeszcze nie zostało zaimplementowane");
    }
}
