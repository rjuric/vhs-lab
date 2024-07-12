package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.repository.VhsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VhsServiceImpl implements VhsService {
    @Autowired
    private VhsRepository repository;

    @Override
    public List<Vhs> getAll() {
        return repository.findAll();
    }

    @Override
    public Vhs getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Vhs create(String name, String description) {
        Vhs newVhs = new Vhs(name, description);
        return repository.save(newVhs);
    }

    @Override
    public Vhs update(Long id, String name, String description) {
        Vhs entity = new Vhs(name, description);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
