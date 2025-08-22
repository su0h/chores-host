package com.su0h.Chores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.su0h.Chores.entity.ApiKey;
import org.springframework.data.jpa.repository.Query;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    @Query(value = "SELECT * from api_keys ak where ak.active = TRUE", nativeQuery = true)
    List<ApiKey> findAllByActiveTrue();

    ApiKey findByKeyHash(String keyHash);
}

