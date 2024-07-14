package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.CreateRentalDTO;
import com.rjuric.vhs_lab.dtos.ReturnRentalDTO;
import com.rjuric.vhs_lab.dtos.UpdateRentalDTO;
import com.rjuric.vhs_lab.entities.Rental;
import com.rjuric.vhs_lab.services.RentalsService;
import com.rjuric.vhs_lab.util.responses.RentalBill;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Rental create(@Valid @RequestBody CreateRentalDTO body) {
        return service.create(body.getVhsId(), body.getUserId(), body.getStartDate(), body.getEndDate());
    }

    @PostMapping("/{id}/return")
    public RentalBill rent(@PathVariable long id, @Valid @RequestBody ReturnRentalDTO body) {
        return service.returnVhs(id, body.getUserId());
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
}
