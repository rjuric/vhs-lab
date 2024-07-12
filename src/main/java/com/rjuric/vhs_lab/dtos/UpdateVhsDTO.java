package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateVhsDTO {

    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Name is required.")
    @NotEmpty(message = "Name should not be empty.")
    private String name;

    @NotNull(message = "Description is required.")
    @NotEmpty(message = "Description should not be empty.")
    @Size(min=20, max=120, message = "Description should be between 20 and 120 characters long.")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
