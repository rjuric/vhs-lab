package com.rjuric.vhs_lab.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignUpDTO {
    @Schema(example = "email@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.isEmail}")
    private String email;

    @Schema(example = "strong_password", requiredMode = Schema.RequiredMode.REQUIRED, minLength = 12)
    @NotBlank(message = "{password.notBlank}")
    @Length(min = 12, message = "{password.length}")
    private String password;
}
