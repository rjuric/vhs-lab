package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.controllers.VhsController;
import com.rjuric.vhs_lab.util.errors.VhsNotFoundException;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {VhsController.class})
public class VhsControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(VhsNotFoundException exc) {
        GenericHttpErrorResponse response = new GenericHttpErrorResponse();

        response.setMessage(exc.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
