package pl.webapp.shop.common.exception_handling;

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
