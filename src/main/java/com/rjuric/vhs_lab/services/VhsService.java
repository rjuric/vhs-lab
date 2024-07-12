package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Vhs;

import java.util.List;

public interface VhsService {
    List<Vhs> getAll();

    Vhs getById(long id);

    Vhs create(String name, String description);

    Vhs update(Long id, String name, String description);

    void delete(long id);
}