package com.rjuric.vhs_lab.controllers;

import com.rjuric.vhs_lab.dtos.CreateVhsDTO;
import com.rjuric.vhs_lab.dtos.UpdateVhsDTO;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.repository.VhsRepository;
import com.rjuric.vhs_lab.services.VhsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vhs")
public class VhsController {

    @Autowired
    private VhsService vhsService;

    @GetMapping
    public List<Vhs> getAll() {
        return vhsService.getAll();
    }

    @GetMapping("/{id}")
    public Vhs getById(@PathVariable long id) {
        return vhsService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Vhs create(@Valid @RequestBody CreateVhsDTO body) {
        return vhsService.create(body.getName(), body.getDescription());
    }

    @PutMapping
    public Vhs update(@Valid @RequestBody UpdateVhsDTO body) {
        return vhsService.update(body.getId(), body.getName(), body.getDescription());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        vhsService.delete(id);
    }
}