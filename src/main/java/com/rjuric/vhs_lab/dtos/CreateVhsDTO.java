package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class CreateVhsDTO {
    @NotBlank(message = "{name.notBlank}")
    private String name;

    @NotBlank(message = "{description.notBlank}")
    @Length(min=20, max=120, message = "{description.length}")
    private String description;
}
