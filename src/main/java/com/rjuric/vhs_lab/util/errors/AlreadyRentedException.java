package com.rjuric.vhs_lab.util.errors;

public class AlreadyRentedException extends RuntimeException {
    public AlreadyRentedException(String message) {
        super(message);
    }
}
