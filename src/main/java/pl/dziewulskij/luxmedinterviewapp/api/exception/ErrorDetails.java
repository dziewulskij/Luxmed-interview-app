package pl.dziewulskij.luxmedinterviewapp.api.exception;

import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

public record ErrorDetails(OffsetDateTime timestamp, HttpStatus status, String message) {

    public static ErrorDetails of(HttpStatus status, String message) {
        return new ErrorDetails(OffsetDateTime.now(), status, message);
    }

}
