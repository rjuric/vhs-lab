package com.rjuric.vhs_lab.repository;

import com.rjuric.vhs_lab.entities.Vhs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VhsRepository extends JpaRepository<Vhs, Long> {}
