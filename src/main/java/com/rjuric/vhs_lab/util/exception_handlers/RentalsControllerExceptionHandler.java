package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.controllers.RentalsController;
import com.rjuric.vhs_lab.util.errors.AlreadyRentedException;
import com.rjuric.vhs_lab.util.errors.RentalNotFoundException;
import com.rjuric.vhs_lab.util.errors.RentalNotFoundOrAlreadyReturnedException;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(assignableTypes = {RentalsController.class})
public class RentalsControllerExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(AlreadyRentedException exc, Locale locale) {
        logError(exc);

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage(exc.getMessage(), null, locale))
                .status(HttpStatus.CONFLICT.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(RentalNotFoundException exc, Locale locale) {
        logError(exc);

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage(exc.getMessage(), null, locale))
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(
            RentalNotFoundOrAlreadyReturnedException exc,
            Locale locale
    ) {
        logError(exc);

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage(exc.getMessage(), null, locale))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(DataIntegrityViolationException exc, Locale locale) {
        logError(exc);

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage("common.badRequest", null, locale))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private void logError(RuntimeException exception) {
        log.error("RENTALS ERROR -> {}", exception.getMessage());
    }
}
