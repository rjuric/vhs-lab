package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class CreateRentalDTO {
    @NotNull(message = "{vhsId.notNull}")
    @Positive(message = "{vhsId.positive}")
    private Long vhsId;

    @NotNull(message = "{startDate.notNull}")
    @Future(message = "{startDate.future")
    private Date startDate;

    @NotNull(message = "{endDate.notNull}")
    @Future(message = "{endDate.future}")
    private Date endDate;
}
