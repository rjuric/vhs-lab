package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReturnRentalDTO {
    @NotNull(message = "{userId.notNull}")
    @Positive(message = "{userId.positive}")
    private Long userId;
}
