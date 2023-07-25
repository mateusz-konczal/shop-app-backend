package pl.webapp.shop.common.exception_handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.NoSuchElementException;

@RestControllerAdvice
class DefaultErrorHandler {

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<DefaultErrorDto> handleNoSuchElementException(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createDefaultErrorDto(HttpStatus.NOT_FOUND, "Zasób nie istnieje", request));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    ResponseEntity<DefaultErrorDto> handleSqlIntegrityConstraintViolationException(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createDefaultErrorDto(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Naruszono reguły integralności bazy danych", request));
    }

    private static DefaultErrorDto createDefaultErrorDto(HttpStatus status, String message, HttpServletRequest request) {
        return DefaultErrorDto.builder()
                .timestamp(new Date())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();
    }
}
