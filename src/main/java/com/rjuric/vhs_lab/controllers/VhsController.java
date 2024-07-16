package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.CreateVhsDTO;
import com.rjuric.vhs_lab.dtos.UpdateVhsDTO;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.services.VhsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/vhs")
public class VhsController {

    private final VhsService service;

    @Autowired
    public VhsController(VhsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vhs> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Vhs getById(@PathVariable long id) {
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Vhs create(@Valid @RequestBody CreateVhsDTO body) {
        return service.create(body.getName(), body.getDescription());
    }

    @PutMapping
    public Vhs update(@Valid @RequestBody UpdateVhsDTO body) {
        return service.update(body.getId(), body.getName(), body.getDescription());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}