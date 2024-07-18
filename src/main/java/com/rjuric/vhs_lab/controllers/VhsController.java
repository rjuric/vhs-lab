package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.CreateVhsDTO;
import com.rjuric.vhs_lab.dtos.UpdateVhsDTO;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.services.VhsService;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import com.rjuric.vhs_lab.util.responses.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Vhs controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/vhs")
public class VhsController {

    private final VhsService service;

    @Operation(summary = "Get all of the Vhs in the system.", description = "Returns a list of all the Vhs.")
    @ApiResponse(responseCode = "200", description = "Successfully fetched.")
    @GetMapping
    public List<Vhs> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get a Vhs by ID.", description = "Returns the specific Vhs.")
    @ApiResponse(responseCode = "200", description = "Successfully fetched.")
    @ApiResponse(
            responseCode = "404",
            description = "Vhs not found.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @GetMapping("/{id}")
    public Vhs getById(@PathVariable @Parameter(name = "id", description = "Vhs id", example = "1") long id) {
        return service.getById(id);
    }

    @Operation(summary = "Create a Vhs.", description = "Returns the created Vhs")
    @Parameter(name = "Authorization", description = "Bearer token", required = true, in = ParameterIn.HEADER)
    @ApiResponse(responseCode = "201", description = "Vhs created.")
    @ApiResponse(
            responseCode = "400",
            description = "DTO validation failed.",
            content = { @Content(schema = @Schema(implementation = ValidationErrorResponse.class)) }
    )
    @ApiResponse(
            responseCode = "401",
            description = "Invalid token.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @ApiResponse(
            responseCode = "403",
            description = "User not ADMIN.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Vhs create(@Valid @RequestBody CreateVhsDTO body) {
        return service.create(body.getName(), body.getDescription());
    }

    @Operation(summary = "Update a Vhs.", description = "Returns the updated Vhs")
    @Parameter(name = "Authorization", description = "Bearer token", required = true, in = ParameterIn.HEADER)
    @ApiResponse(responseCode = "200", description = "Vhs updated.")
    @ApiResponse(
            responseCode = "400",
            description = "DTO validation failed.",
            content = { @Content(schema = @Schema(implementation = ValidationErrorResponse.class)) }
    )
    @ApiResponse(
            responseCode = "401",
            description = "Invalid token.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @ApiResponse(
            responseCode = "403",
            description = "User not ADMIN.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @PutMapping
    public Vhs update(@Valid @RequestBody UpdateVhsDTO body) {
        return service.update(body.getId(), body.getName(), body.getDescription());
    }

    @Operation(summary = "Delete a Vhs.", description = "Deletes the Vhs with ID.")
    @Parameter(name = "Authorization", description = "Bearer token", required = true, in = ParameterIn.HEADER)
    @ApiResponse(responseCode = "204", description = "Vhs deleted.")
    @ApiResponse(
            responseCode = "401",
            description = "Invalid token.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @ApiResponse(
            responseCode = "403",
            description = "User not ADMIN.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Parameter(name = "id", description = "Vhs id", example = "1") long id) {
        service.delete(id);
    }
}