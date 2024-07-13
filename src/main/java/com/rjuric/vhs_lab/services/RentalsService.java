package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Rental;
import com.rjuric.vhs_lab.util.responses.RentalBill;

import java.util.Date;
import java.util.List;

public interface RentalsService {
    List<Rental> getAll();

    Rental getById(long id);

    Rental create(long vhsId, long userId, Date startDate, Date endDate);

    Rental update(long id, long vhsId, long userId, Date startDate, Date endDate, Date returnedAt);

    void delete(long id);

    RentalBill returnVhs(long id, Long userId);
}
