package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class CreateVhsDTO {

    @NotBlank(message = "{name.notBlank}")
    private String name;

    @NotBlank(message = "{description.notBlank}")
    @Length(min=20, max=120, message = "{description.length}")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
