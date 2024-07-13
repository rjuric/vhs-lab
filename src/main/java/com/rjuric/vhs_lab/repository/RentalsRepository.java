package com.rjuric.vhs_lab.repository;

import com.rjuric.vhs_lab.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface RentalsRepository extends JpaRepository<Rental, Long> {
    @Query("""
           SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
           FROM Rental r
           WHERE r.startDate <= :endDate
           AND r.endDate >= :startDate
           """)
    boolean existsByStartDateAndEndDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(value = """
            UPDATE rental
            SET returned_at = CURRENT_TIMESTAMP
            WHERE id = :rentalId
            AND user_id = :userId
            AND returned_at IS NULL
            RETURNING *
            """,
            nativeQuery = true)
    Optional<Rental> setReturnedAndFetch(@Param("rentalId") long id, @Param("userId") long userId);
}
