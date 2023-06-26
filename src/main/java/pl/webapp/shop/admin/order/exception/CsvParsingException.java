package pl.webapp.shop.admin.order.exception;

public class CsvParsingException extends RuntimeException {

    public CsvParsingException(String message) {
        super("Nie można utworzyć pliku CSV: " + message);
    }
}
