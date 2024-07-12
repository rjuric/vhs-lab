package com.rjuric.vhs_lab.services;

import org.springframework.stereotype.Service;

@Service
public class RentalsServiceImpl implements RentalsService {

    @Override
    public String getAll() {
        return "All of the rental data.";
    }

    @Override
    public String getById(long id) {
        return "This one specific rental with id: " + id;
    }

    @Override
    public String create() {
        return "Created a rental.";
    }

    @Override
    public String update() {
        return "Updated a rental.";
    }

    @Override
    public String delete(long id) {
        return "Deleted a rental with id: " + id;
    }
}
