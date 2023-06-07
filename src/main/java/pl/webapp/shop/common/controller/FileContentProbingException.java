package pl.webapp.shop.common.controller;

class FileContentProbingException extends RuntimeException {

    FileContentProbingException(String message) {
        super("Nie można określić typu zawartości pliku: " + message);
    }
}
