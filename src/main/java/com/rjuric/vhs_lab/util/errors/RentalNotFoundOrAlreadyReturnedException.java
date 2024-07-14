package com.rjuric.vhs_lab.util.errors;

public class RentalNotFoundOrAlreadyReturnedException extends RuntimeException {
    public RentalNotFoundOrAlreadyReturnedException(String message) {
        super(message);
    }
}
