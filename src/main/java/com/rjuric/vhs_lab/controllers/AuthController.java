package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.LoginDTO;
import com.rjuric.vhs_lab.dtos.SignUpDTO;
import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersService service;

    @PostMapping("/sign-up")
    public User signUp(@Valid @RequestBody SignUpDTO body) {
        return service.create(body.getEmail(), body.getPassword());
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginDTO body) {
        return service.findByEmailAndPassword(body.getEmail(), body.getPassword());
    }

}
