package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.*;

public class CreateVhsDTO {

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotBlank(message = "description should not be blank")
    @Size(min=20, max=120, message = "description should be between 20 and 120 characters long")
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
