package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.controllers.AuthController;
import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
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
@RestControllerAdvice(assignableTypes = {AuthController.class})
public class AuthControllerExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(AuthException exc, Locale locale) {
        log.error("AUTH ERROR -> {}", exc.getMessage());

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage("auth.incorrect.emailOrPassword", null, locale))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(UserNotFoundException exc, Locale locale) {
        log.error("AUTH ERROR -> {}", exc.getMessage());

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage("auth.incorrect.emailOrPassword", null, locale))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(DataIntegrityViolationException exc, Locale locale) {
        log.error("AUTH ERROR -> {}", exc.getMessage());

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage("auth.user.conflict", null, locale))
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
