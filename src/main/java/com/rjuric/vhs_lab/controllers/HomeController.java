package com.rjuric.vhs_lab.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Home controller")
@RestController
@RequestMapping("/")
public class HomeController {

    @Operation(summary = "Say hi to mom.")
    @ApiResponse(responseCode = "200", description = "Mom is happy.")
    @GetMapping
    public String getHome() {
        return "Hi mom! ";
    }
}
