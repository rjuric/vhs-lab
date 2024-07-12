package com.rjuric.vhs_lab.services;

public interface VhsService {
    String getAll();

    String getById(long id);

    String create();

    String update();

    String delete(long id);
}