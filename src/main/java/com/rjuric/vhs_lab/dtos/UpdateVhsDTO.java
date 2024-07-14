package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateVhsDTO {

    @NotNull(message = "{id.notNull}")
    @Positive(message = "{id.positive}")
    private Long id;

    @NotBlank(message = "{name.notBlank}")
    private String name;

    @NotBlank(message = "{description.notBlank}")
    @Length(min=20, max=120, message = "{description.length}")
    private String description;
}
