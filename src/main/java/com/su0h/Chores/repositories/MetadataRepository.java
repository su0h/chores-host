package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.Holiday;
import com.su0h.Chores.entities.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MetadataRepository extends JpaRepository<Metadata, String> {
    @Query(value = "SELECT value FROM metadata WHERE key = :key", nativeQuery = true)
    String findValueByKey(@Param("key") String key);
}
