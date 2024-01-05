package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query(value = "SELECT * from holiday WHERE date = :date", nativeQuery = true)
    Holiday findByDate(@Param("date") LocalDate date);
}
