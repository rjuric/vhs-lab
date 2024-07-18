package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import com.rjuric.vhs_lab.util.responses.ValidationErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<ValidationErrorResponse> handleException(MethodArgumentNotValidException exc, Locale locale) {
        log.error("VALIDATION ERROR -> {}", exc.getMessage());

        List<String> mappedFields = exc
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .message(messageSource.getMessage("validation.failed", null, locale))
                .status(HttpStatus.BAD_REQUEST.value())
                .fields(mappedFields)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(UserNotFoundException exc, Locale locale) {
        log.error("USER NOT FOUND ERROR -> {}", exc.getMessage());

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage("user.notFound", null, locale))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
