package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.services.VhsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vhs")
public class VhsController {

    @Autowired
    private VhsService vhsService;

    // TODO: Implement
    @GetMapping
    public String getAll() {
        return vhsService.getAll();
    }

    // TODO: Implement
    @GetMapping("/{id}")
    public String getById(@PathVariable long id) {
        return vhsService.getById(id);
    }

    // TODO: Implement
    @PostMapping
    public String create() {
        return vhsService.create();
    }

    // TODO: Implement
    @PutMapping
    public String update() {
        return vhsService.update();
    }

    // TODO: Implement
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        return vhsService.delete(id);
    }
}