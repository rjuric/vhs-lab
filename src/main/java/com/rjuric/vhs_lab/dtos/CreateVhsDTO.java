package com.rjuric.vhs_lab.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class CreateVhsDTO {
    @Schema(example = "Godzilla", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{name.notBlank}")
    private String name;

    @Schema(
            example = "A giant lizard wreaks havoc on New York City and it's residents.",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 20,
            maxLength = 120
    )
    @NotBlank(message = "{description.notBlank}")
    @Length(min=20, max=120, message = "{description.length}")
    private String description;
}
