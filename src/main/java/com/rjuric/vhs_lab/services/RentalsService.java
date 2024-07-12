package com.rjuric.vhs_lab.services;

public interface RentalsService {
    String getAll();

    String getById(long id);

    String create();

    String update();

    String delete(long id);
}
