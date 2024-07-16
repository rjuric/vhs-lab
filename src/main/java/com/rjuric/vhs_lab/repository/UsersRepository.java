package com.rjuric.vhs_lab.repository;

import com.rjuric.vhs_lab.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    @Transactional
    Optional<User> findByEmail(String email);
}