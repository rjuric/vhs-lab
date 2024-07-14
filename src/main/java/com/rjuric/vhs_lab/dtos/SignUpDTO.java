package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class SignUpDTO {

    @NotBlank(message = "{email.notBlank}")
    @Email(message = "{email.isEmail}")
    private String email;

    @NotBlank(message = "{password.notBlank}")
    @Length(min = 12, message = "{password.length")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
