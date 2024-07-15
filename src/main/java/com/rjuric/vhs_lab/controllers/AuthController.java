package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.LoginDTO;
import com.rjuric.vhs_lab.dtos.SignUpDTO;
import com.rjuric.vhs_lab.services.AuthService;
import com.rjuric.vhs_lab.util.responses.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@Valid @RequestBody SignUpDTO body) {
        return service.create(body.getEmail(), body.getPassword());
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginDTO body) {
        return service.findByEmailAndPassword(body.getEmail(), body.getPassword());
    }

}
