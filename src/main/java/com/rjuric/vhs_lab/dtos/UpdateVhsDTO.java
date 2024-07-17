package com.rjuric.vhs_lab.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class UpdateVhsDTO {

    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED, minimum = "0")
    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    private Long id;

    @Schema(example = "Godzilla 2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{name.notBlank}")
    private String name;

    @Schema(
            example = "A giant lizard wreaks havoc on New York City and it's residents 2.",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 20,
            maxLength = 120
    )
    @NotBlank(message = "{description.notBlank}")
    @Length(min=20, max=120, message = "{description.length}")
    private String description;
}
