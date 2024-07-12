package com.rjuric.vhs_lab.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class SignUpDTO {

    @NotBlank(message = "email should not be blank")
    @Email(message = "email needs to be a valid email")
    private String email;

    @NotBlank(message = "password should not be blank")
    @Length(min = 12, message = "password should be at least 12 characters long")
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
