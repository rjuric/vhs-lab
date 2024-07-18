package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.controllers.VhsController;
import com.rjuric.vhs_lab.util.errors.VhsNotFoundException;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice(assignableTypes = {VhsController.class})
@RequiredArgsConstructor
public class VhsControllerExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(VhsNotFoundException exc, Locale locale) {
        log.error("VHS ERROR -> {}", exc.getMessage());

        GenericHttpErrorResponse response = GenericHttpErrorResponse.builder()
                .message(messageSource.getMessage(exc.getMessage(), null, locale))
                .status(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
