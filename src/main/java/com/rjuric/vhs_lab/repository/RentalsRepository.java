package com.rjuric.vhs_lab.repository;

import com.rjuric.vhs_lab.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalsRepository extends JpaRepository<Rental, Long> {}
