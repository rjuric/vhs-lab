package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.controllers.VhsController;
import com.rjuric.vhs_lab.util.errors.VhsNotFoundException;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
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
public class VhsControllerExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(VhsNotFoundException exc, Locale locale) {
        log.error("VHS ERROR: {}", exc.getMessage());

        GenericHttpErrorResponse response = new GenericHttpErrorResponse();

        response.setMessage(messageSource.getMessage(exc.getMessage(), null, locale));
        response.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
