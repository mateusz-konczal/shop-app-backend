package pl.webapp.shop.common.exception_handler;

import lombok.Builder;

import java.util.Date;

@Builder
record DefaultErrorDto(

        Date timestamp,
        int status,
        String error,
        String message,
        String path) {
}
