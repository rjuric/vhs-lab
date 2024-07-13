package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ReturnRentalDTO {
    @NotNull(message = "userId is required")
    @Positive(message = "userId needs to be positive")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
