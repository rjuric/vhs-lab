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

    @Autowired
    private RentalsService rentalsService;

    @GetMapping
    public List<Rental> getAll() {
        return rentalsService.getAll();
    }

    @GetMapping("/{id}")
    public Rental getById(@PathVariable long id) {
        return rentalsService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rental create(@Valid @RequestBody CreateRentalDTO body) {
        return rentalsService.create(body.getVhsId(), body.getUserId(), body.getStartDate(), body.getEndDate());
    }

    @PostMapping("/{id}/return")
    public RentalBill rent(@PathVariable long id, @Valid @RequestBody ReturnRentalDTO body) {
        return rentalsService.returnVhs(id, body.getUserId());
    }

    @PutMapping
    public Rental update(@Valid @RequestBody UpdateRentalDTO body) {
        return rentalsService.update(
                body.getId(),
                body.getVhsId(),
                body.getUserId(),
                body.getStartDate(),
                body.getEndDate()
        );
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        rentalsService.delete(id);
    }
}
