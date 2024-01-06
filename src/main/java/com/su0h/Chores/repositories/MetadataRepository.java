package com.su0h.Chores.repositories;

import com.su0h.Chores.entities.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MetadataRepository extends JpaRepository<Metadata, String> {
    @Query(value = "SELECT value FROM metadata WHERE key = 'lastModified'", nativeQuery = true)
    String getLastModifiedDateAsString();

    // Default method to convert String to LocalDate
    default LocalDate getLastModifiedDate() {
        String dateString = getLastModifiedDateAsString();

        // Assuming the date is stored in a format like 'yyyy-MM-dd'
        return LocalDate.parse(dateString);
    }
}
