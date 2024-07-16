package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignUpDTO {
    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.isEmail}")
    private String email;

    @NotBlank(message = "{password.notBlank}")
    @Length(min = 12, message = "{password.length}")
    private String password;
}
