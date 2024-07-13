package com.rjuric.vhs_lab.util.errors;

public class RentalNotFoundOrAlreadyReturnedException extends RuntimeException {
    public RentalNotFoundOrAlreadyReturnedException() {
        super("rental not found or already returned");
    }
}
