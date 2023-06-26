package pl.webapp.shop.common.controller.exception;

public class FileContentProbingException extends RuntimeException {

    public FileContentProbingException(String message) {
        super("Nie można określić typu zawartości pliku: " + message);
    }
}
