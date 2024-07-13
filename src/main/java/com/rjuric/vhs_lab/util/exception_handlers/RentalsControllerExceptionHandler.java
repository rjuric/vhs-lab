package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.controllers.RentalsController;
import com.rjuric.vhs_lab.util.errors.AlreadyRentedException;
import com.rjuric.vhs_lab.util.errors.RentalNotFoundException;
import com.rjuric.vhs_lab.util.errors.RentalNotFoundOrAlreadyReturnedException;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import org.hibernate.JDBCException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {RentalsController.class})
public class RentalsControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(AlreadyRentedException exc) {
        GenericHttpErrorResponse response = new GenericHttpErrorResponse();

        response.setMessage(exc.getMessage());
        response.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(RentalNotFoundException exc) {
        GenericHttpErrorResponse response = new GenericHttpErrorResponse();

        response.setMessage(exc.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(RentalNotFoundOrAlreadyReturnedException exc) {
        GenericHttpErrorResponse response = new GenericHttpErrorResponse();

        response.setMessage(exc.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GenericHttpErrorResponse> handleException(DataIntegrityViolationException exc) {
        GenericHttpErrorResponse response = new GenericHttpErrorResponse();

        response.setMessage("bad request");
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
