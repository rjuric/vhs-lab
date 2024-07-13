package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Rental;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.repository.RentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentalsServiceImpl implements RentalsService {

    @Autowired
    private RentalsRepository repository;

    @Override
    public List<Rental> getAll() {
        return repository.findAll();
    }

    @Override
    public Rental getById(long id) {
        return repository.findById(id).orElse(null);
    }

    // TODO: check if dates are already taken
    @Override
    public Rental create(long vhsId, long userId, Date startDate, Date endDate) {
        Rental entity = new Rental(startDate, endDate, userId, vhsId);
        return repository.save(entity);
    }

    @Override
    public Rental update(long id, long vhsId, long userId, Date startDate, Date endDate) {
        Rental entity = new Rental();

        Vhs updatedVhs = new Vhs();
        updatedVhs.setId(vhsId);

        entity.setVhs(updatedVhs);
        entity.setUserId(userId);
        entity.setStartDate(startDate);
        entity.setEndDate(endDate);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
