package pl.webapp.shop.common.exception_handling;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
class DefaultErrorHandling {

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<DefaultErrorDto> handleNoSuchElementException(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DefaultErrorDto.builder()
                        .timestamp(new Date())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .message("Zasób nie istnieje")
                        .path(request.getRequestURI())
                        .build());
    }
}
