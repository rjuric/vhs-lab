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
           AND r.vhs.id = :vhsId
           """)
    boolean existsByStartDateAndEndDate(@Param("vhsId") long vhsId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(value = """
            UPDATE rental
            SET returned_at = :returnedAt
            WHERE id = :rentalId
            AND user_id = :userId
            AND returned_at IS NULL
            RETURNING *
            """,
            nativeQuery = true)
    Optional<Rental> setReturnedAndFetch(
            @Param("rentalId") long id,
            @Param("userId") long userId,
            @Param("returnedAt") Date returnedAt
    );

    @Query(value = """
            UPDATE rental
            SET start_date = :startDate,
            end_date = :endDate,
            returned_at = :returnedAt,
            vhs_id = :vhsId,
            user_id = :userId
            WHERE id = :id
            RETURNING *
            """,
            nativeQuery = true)
    Optional<Rental> update(
            @Param("id") long id,
            @Param("vhsId") long vhsId,
            @Param("userId") long userId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("returnedAt") Date returnedAt
    );
}
