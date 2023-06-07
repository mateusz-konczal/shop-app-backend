package pl.webapp.shop.admin.product.exception;

public class FileNotSavedException extends RuntimeException {

    public FileNotSavedException(String message) {
        super("Nie można zapisać pliku: " + message);
    }
}
