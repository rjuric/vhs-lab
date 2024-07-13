package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.LoginDTO;
import com.rjuric.vhs_lab.dtos.SignUpDTO;
import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
