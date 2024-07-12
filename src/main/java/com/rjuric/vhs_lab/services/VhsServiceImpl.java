package com.rjuric.vhs_lab.services;

import org.springframework.stereotype.Service;

@Service
public class VhsServiceImpl implements VhsService {
    @Override
    public String getAll() {
        return "All of the VHS tapes we have.";
    }

    @Override
    public String getById(long id) {
        return "This one specific VHS with the id of: " + id;
    }

    @Override
    public String create() {
        return "Created a new VHS.";
    }

    @Override
    public String update() {
        return "Updating a VHS.";
    }

    @Override
    public String delete(long id) {
        return "Deleted the VHS with id of: " + id;
    }
}
