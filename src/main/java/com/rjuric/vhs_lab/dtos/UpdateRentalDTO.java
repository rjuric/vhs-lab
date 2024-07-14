package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateRentalDTO {

    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    private Long id;

    @NotNull(message = "{userId.notNull}")
    @Positive(message = "{userId.positive}")
    private Long userId;

    @NotNull(message = "{vhsId.notNull}")
    @Positive(message = "{vhsId.positive}")
    private Long vhsId;

    @NotNull(message = "{startDate.notNull}")
    @Future(message = "{startDate.future}")
    private Date startDate;

    @NotNull(message = "{endDate.notNull}")
    @Future(message = "{endDate.future}")
    private Date endDate;

    @PastOrPresent(message = "{returnedAt.pastOrPresent}")
    private Date returnedAt;
}
