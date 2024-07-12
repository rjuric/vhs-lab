package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.services.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;

    // TODO: Implement
    @GetMapping
    public String getAll() {
        return rentalsService.getAll();
    }

    // TODO: Implement
    @GetMapping("/{id}")
    public String getById(@PathVariable long id) {
        return rentalsService.getById(id);
    }

    // TODO: Implement
    @PostMapping
    public String create() {
        return rentalsService.create();
    }

    // TODO: Implement
    @PutMapping
    public String update() {
        return rentalsService.update();
    }

    // TODO: Implement
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        return rentalsService.delete(id);
    }
}
