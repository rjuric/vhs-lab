package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.repository.VhsRepository;
import com.rjuric.vhs_lab.util.errors.VhsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VhsServiceImpl implements VhsService {

    private final VhsRepository repository;

    @Override
    public List<Vhs> getAll() {
        return repository.findAll();
    }

    @Override
    public Vhs getById(long id) {
        return repository.findById(id).orElseThrow(() -> new VhsNotFoundException("vhs.notFound"));
    }

    @Override
    public Vhs create(String name, String description) {
        Vhs newVhs = Vhs.builder().name(name).description(description).build();
        return repository.save(newVhs);
    }

    @Override
    public Vhs update(Long id, String name, String description) {
        Vhs entity = Vhs.builder().name(name).description(description).build();
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
