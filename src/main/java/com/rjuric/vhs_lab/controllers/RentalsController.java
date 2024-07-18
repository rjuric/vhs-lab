package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.CreateRentalDTO;
import com.rjuric.vhs_lab.dtos.UpdateRentalDTO;
import com.rjuric.vhs_lab.entities.Rental;
import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.services.RentalsService;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import com.rjuric.vhs_lab.util.responses.GenericHttpErrorResponse;
import com.rjuric.vhs_lab.util.responses.RentalBill;
import com.rjuric.vhs_lab.util.responses.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "Rentals controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalsController {

    private final RentalsService service;

    @Operation(summary = "Get a list of all rentals.", description = "Returns a list of all rentals.")
    @GetMapping
    public List<Rental> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get a specific Rental.", description = "Returns a Rental with a specific ID.")
    @ApiResponse(responseCode = "200", description = "Rental with ID returned.")
    @ApiResponse(
            responseCode = "404",
            description = "Rental not found.",
            content = { @Content (schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @GetMapping("/{id}")
    public Rental getById(@PathVariable  @Parameter(name = "id", description = "Rental id", example = "1") long id) {
        return service.getById(id);
    }

    @Operation(summary = "Create a Rental.", description = "Returns the created Rental.")
    @Parameter(name = "Authorization", description = "Bearer token", required = true, in = ParameterIn.HEADER)
    @ApiResponse(responseCode = "201", description = "Rental created.")
    @ApiResponse(
            responseCode = "400",
            description = "DTO validation failed.",
            content = { @Content (schema = @Schema(implementation = ValidationErrorResponse.class)) }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rental create(Principal principal, @Valid @RequestBody CreateRentalDTO body) {
        return service.create(body.getVhsId(), getUserId(principal), body.getStartDate(), body.getEndDate());
    }
    @Operation(summary = "Return a Rental.", description = "Returns the payment information for late fees.")
    @Parameter(name = "Authorization", description = "Bearer token", required = true, in = ParameterIn.HEADER)
    @ApiResponse(responseCode = "200", description = "Rental returned.")
    @ApiResponse(
            responseCode = "400",
            description = "DTO validation failed.",
            content = { @Content (schema = @Schema(implementation = ValidationErrorResponse.class)) }
    )    @ApiResponse(
            responseCode = "401",
            description = "Invalid token.",
            content = { @Content(schema = @Schema(implementation = GenericHttpErrorResponse.class)) }
    )
    @PostMapping("/{id}/return")
    public RentalBill rent(Principal principal, @PathVariable @Parameter(name = "id", description = "Rental id", example = "1") long id) {
        return service.returnVhs(id, getUserId(principal));
    }

    @Operation(summary = "Update a Rental.", description = "Returns the updated Rental.")
    @Parameter(name = "Authorization", description = "Bearer token", required = true, in = ParameterIn.HEADER)
    @ApiResponse(responseCode = "200", description = "Rental updated.")
    @ApiResponse(
            responseCode = "400",
            description = "DTO validation failed.",
            content = { @Content (schema = @Schema(implementation = ValidationErrorResponse.class)) }
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
    public Rental update(@Valid @RequestBody UpdateRentalDTO body) {
        return service.update(
                body.getId(),
                body.getVhsId(),
                body.getUserId(),
                body.getStartDate(),
                body.getEndDate(),
                body.getReturnedAt()
        );
    }

    @Operation(summary = "Delete a Rental.", description = "Deletes a rental.")
    @Parameter(name = "Authorization", description = "Bearer token", required = true, in = ParameterIn.HEADER)
    @ApiResponse(responseCode = "204", description = "Rental deleted.")
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
    public void delete(@PathVariable @Parameter(name = "id", description = "Rental id", example = "1") long id) {
        service.delete(id);
    }

    private Long getUserId(Principal principal) throws UserNotFoundException {
        if (principal instanceof UsernamePasswordAuthenticationToken auth && auth.getPrincipal() instanceof User user) {
            return user.getId();
        }

        throw new UserNotFoundException("user.notFound");
    }
}
