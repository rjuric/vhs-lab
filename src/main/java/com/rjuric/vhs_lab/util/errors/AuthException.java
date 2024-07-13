package com.rjuric.vhs_lab.util.errors;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
