package com.rjuric.vhs_lab.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateRentalDTO {

    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    private Long id;

    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
    @NotNull(message = "{userId.notNull}")
    @Positive(message = "{userId.positive}")
    private Long userId;

    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
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

    @Schema(example = "2024-09-20", requiredMode = Schema.RequiredMode.REQUIRED)
    @PastOrPresent(message = "{returnedAt.pastOrPresent}")
    private Date returnedAt;
}
