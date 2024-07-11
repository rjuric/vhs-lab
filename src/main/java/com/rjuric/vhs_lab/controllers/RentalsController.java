package com.rjuric.vhs_lab.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    // TODO: Implement
    @GetMapping("/")
    public String getAll() {
        return "No rentals available right now.";
    }

    // TODO: Implement
    @GetMapping("/{id}")
    public String getById(@PathVariable long id) {
        return "No rental with " + id + " available right now.";
    }

    // TODO: Implement
    @PostMapping("/")
    public String create() {
        return "Created a new rental";
    }

    // TODO: Implement
    @PutMapping("/")
    public String update() {
        return "Updated a rental";
    }

    // TODO: Implement
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        return "Deleted a rental";
    }
}
