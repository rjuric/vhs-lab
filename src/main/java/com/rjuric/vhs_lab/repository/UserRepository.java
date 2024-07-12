package com.rjuric.vhs_lab.repository;

import com.rjuric.vhs_lab.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}