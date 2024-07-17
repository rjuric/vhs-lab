package com.rjuric.vhs_lab.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @Schema(example = "email@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.isEmail}")
    private String email;

    @Schema(example = "any_password", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{password.notBlank}")
    private String password;
}
