package pl.webapp.shop.admin.product.exception;

public class FileNotUploadedException extends RuntimeException {

    public FileNotUploadedException(String message) {
        super("Coś poszło nie tak podczas przesyłania pliku: " + message);
    }
}
