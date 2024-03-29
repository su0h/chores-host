package com.su0h.Chores.services;

import com.su0h.Chores.entities.Metadata;
import com.su0h.Chores.repositories.MetadataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MetadataService {
    private final MetadataRepository metadataRepository;

    public MetadataService (MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public LocalDateTime getLastModifiedDate() {
        return LocalDateTime.parse(this.metadataRepository.findValueByKey(Metadata.Key.LAST_MODIFIED.name()));
    }

    public LocalDate getLastUnshifted() {
        return LocalDate.parse(this.metadataRepository.findValueByKey(Metadata.Key.LAST_UNSHIFTED.name()));
    }
}
