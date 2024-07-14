package com.rjuric.vhs_lab.services;

import com.rjuric.vhs_lab.entities.Rental;
import com.rjuric.vhs_lab.entities.Vhs;
import com.rjuric.vhs_lab.repository.RentalsRepository;
import com.rjuric.vhs_lab.util.errors.AlreadyRentedException;
import com.rjuric.vhs_lab.util.errors.RentalNotFoundException;
import com.rjuric.vhs_lab.util.errors.RentalNotFoundOrAlreadyReturnedException;
import com.rjuric.vhs_lab.util.responses.RentalBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class RentalsServiceImpl implements RentalsService {

    private final RentalsRepository repository;

    @Autowired
    public RentalsServiceImpl(RentalsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rental> getAll() {
        return repository.findAll();
    }

    @Override
    public Rental getById(long id) {
        return repository.findById(id).orElseThrow(() -> new RentalNotFoundException("rental.notFound"));
    }

    @Override
    public Rental create(long vhsId, long userId, Date startDate, Date endDate) {
        boolean isAlreadyRented = repository.existsByStartDateAndEndDate(vhsId, startDate, endDate);

        if (isAlreadyRented) {
            throw new AlreadyRentedException("rental.conflict");
        }

        Rental entity = new Rental(startDate, endDate, userId, vhsId);
        return repository.save(entity);
    }

    @Override
    public RentalBill returnVhs(long id, Long userId) {
        Rental rental = repository
                .setReturnedAndFetch(id, userId, new Date())
                .orElseThrow(() -> new RentalNotFoundOrAlreadyReturnedException("rental.notFoundOrAlreadyExists"));

        LocalDate returnedAt = rental.getReturnedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = rental.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(endDate, returnedAt);

        return new RentalBill(daysBetween > 0 ? daysBetween : 0, 5);
    }

    @Override
    public Rental update(long id, long vhsId, long userId, Date startDate, Date endDate, Date returnedAt) {
        return repository
                .update(id, vhsId, userId, startDate, endDate, returnedAt)
                .orElseThrow(() -> new RentalNotFoundException("rental.notFound"));
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
