package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.CreateRentalDTO;
import com.rjuric.vhs_lab.dtos.UpdateRentalDTO;
import com.rjuric.vhs_lab.entities.Rental;
import com.rjuric.vhs_lab.entities.User;
import com.rjuric.vhs_lab.services.RentalsService;
import com.rjuric.vhs_lab.util.errors.UserNotFoundException;
import com.rjuric.vhs_lab.util.responses.RentalBill;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    private final RentalsService service;

    @Autowired
    public RentalsController(RentalsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Rental> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Rental getById(@PathVariable long id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rental create(Principal principal, @Valid @RequestBody CreateRentalDTO body) {
        return service.create(body.getVhsId(), getUserId(principal), body.getStartDate(), body.getEndDate());
    }

    @PostMapping("/{id}/return")
    public RentalBill rent(Principal principal, @PathVariable long id) {
        return service.returnVhs(id, getUserId(principal));
    }

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    private Long getUserId(Principal principal) throws UserNotFoundException {
        if (principal instanceof UsernamePasswordAuthenticationToken auth && auth.getPrincipal() instanceof User user) {
            return user.getId();
        }

        throw new UserNotFoundException("user.notFound");
    }
}
