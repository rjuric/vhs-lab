package com.rjuric.vhs_lab.util.exception_handlers;

import com.rjuric.vhs_lab.controllers.AuthController;
import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import com.rjuric.vhs_lab.util.responses.AuthErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {AuthController.class})
public class AuthControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AuthErrorResponse> handleException(AuthException exc) {
        AuthErrorResponse response = new AuthErrorResponse();

        response.setMessage("incorrect username or password");
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AuthErrorResponse> handleException(UserNotFoundException exc) {
        AuthErrorResponse response = new AuthErrorResponse();

        response.setMessage("incorrect username or password");
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AuthErrorResponse> handleException(DataIntegrityViolationException exc) {
        AuthErrorResponse response = new AuthErrorResponse();

        response.setMessage("user already exists"); // in an ideal world we would send an email to the user
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
