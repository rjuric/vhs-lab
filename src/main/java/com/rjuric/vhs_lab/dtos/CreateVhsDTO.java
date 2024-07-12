package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.*;

public class CreateVhsDTO {

    @NotNull(message = "Name is required.")
    @NotEmpty(message = "Name should not be empty.")
    private String name;

    @NotNull(message = "Description is required.")
    @NotEmpty(message = "Description should not be empty.")
    @Size(min=20, max=120, message = "Description should be between 20 and 120 characters long.")
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
