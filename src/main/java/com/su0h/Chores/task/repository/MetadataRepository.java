package com.su0h.Chores.task.repository;

import com.su0h.Chores.task.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MetadataRepository extends JpaRepository<Metadata, String> {
    @Query(value = "SELECT value FROM metadata WHERE key = :key", nativeQuery = true)
    String findValueByKey(@Param("key") String key);
}
