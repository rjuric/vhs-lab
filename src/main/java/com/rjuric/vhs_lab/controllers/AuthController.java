package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.LoginDTO;
import com.rjuric.vhs_lab.dtos.SignUpDTO;
import com.rjuric.vhs_lab.services.AuthService;
import com.rjuric.vhs_lab.util.responses.AuthenticationResponse;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import com.rjuric.vhs_lab.util.responses.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth controller")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @Operation(summary = "Create an account.", description = "Returns the created user and a token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created."),
            @ApiResponse(
                    responseCode = "400",
                    description = "DTO validation failed.",
                    content = { @Content (schema = @Schema(implementation = ValidationErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User already exists.",
                    content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@Valid @RequestBody SignUpDTO body) {
        return service.create(body.getEmail(), body.getPassword());
    }

    @Operation(summary = "Log in.", description = "Returns the logged in user and a token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in."),
            @ApiResponse(
                    responseCode = "400",
                    description = "DTO validation failed.",
                    content = { @Content (schema = @Schema(implementation = ValidationErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User doesn't exist.",
                    content = { @Content (schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect password.",
                    content = { @Content (schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
            )
    })
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginDTO body) {
        return service.findByEmailAndPassword(body.getEmail(), body.getPassword());
    }

}
