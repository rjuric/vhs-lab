package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.LoginDTO;
import com.rjuric.vhs_lab.dtos.SignUpDTO;
import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.services.AuthService;
import com.rjuric.vhs_lab.util.errors.AuthException;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import com.rjuric.vhs_lab.util.responses.AuthErrorResponse;
import com.rjuric.vhs_lab.util.responses.ValidationErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/sign-up")
    public User signUp(@Valid @RequestBody SignUpDTO body) {
        return service.create(body.getEmail(), body.getPassword());
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginDTO body) {
        return service.findByEmailAndPassword(body.getEmail(), body.getPassword());
    }

}
