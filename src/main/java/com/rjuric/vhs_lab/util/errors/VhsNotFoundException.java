package com.rjuric.vhs_lab.util.errors;

public class VhsNotFoundException extends RuntimeException {
    public VhsNotFoundException(String message) {
        super(message);
    }
}
