package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.isEmail}")
    private String email;

    @NotBlank(message = "{password.notBlank}")
    private String password;
}
