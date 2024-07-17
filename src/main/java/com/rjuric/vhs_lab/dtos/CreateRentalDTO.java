package com.rjuric.vhs_lab.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class CreateRentalDTO {
    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "{vhsId.notNull}")
    @Positive(message = "{vhsId.positive}")
    private Long vhsId;

    @Schema(example = "2024-09-20", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "{startDate.notNull}")
    @Future(message = "{startDate.future}")
    private Date startDate;

    @Schema(example = "2024-09-20", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "{endDate.notNull}")
    @Future(message = "{endDate.future}")
    private Date endDate;
}
