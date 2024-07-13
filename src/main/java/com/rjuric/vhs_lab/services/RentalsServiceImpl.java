package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Rental;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.repository.RentalsRepository;
import com.rjuric.vhs_lab.util.errors.AlreadyRentedException;
import com.rjuric.vhs_lab.util.errors.RentalNotFoundOrAlreadyReturnedException;
import com.rjuric.vhs_lab.util.responses.RentalBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Rental create(long vhsId, long userId, Date startDate, Date endDate) {
        boolean isAlreadyRented = repository.existsByStartDateAndEndDate(startDate, endDate);

        if (isAlreadyRented) {
            throw new AlreadyRentedException("rental already exists in specified period");
        }

        Rental entity = new Rental(startDate, endDate, userId, vhsId);
        return repository.save(entity);
    }

    @Override
    public RentalBill returnVhs(long id, Long userId) {
        Rental rental = repository.setReturnedAndFetch(id, userId).orElseThrow(RentalNotFoundOrAlreadyReturnedException::new);

        LocalDate returnedAt = rental.getReturnedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = rental.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(endDate, returnedAt);

        return new RentalBill(daysBetween > 0 ? daysBetween : 0);
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
