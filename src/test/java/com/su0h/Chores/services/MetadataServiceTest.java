package com.su0h.Chores.services;

import com.su0h.Chores.entities.Metadata;
import com.su0h.Chores.repositories.MetadataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MetadataServiceTest {

    @Mock
    private MetadataRepository metadataRepository;

    @InjectMocks
    private MetadataService metadataService;

    @Test
    public void testGetLastModifiedDate() {
        String expectedDateStr = "2026-07-03T12:00:00";
        when(metadataRepository.findValueByKey(Metadata.Key.LAST_MODIFIED.name()))
            .thenReturn(expectedDateStr);
            
        LocalDateTime result = metadataService.getLastModifiedDate();
        assertEquals(LocalDateTime.parse(expectedDateStr), result);
    }
    
    @Test
    public void testGetLastUnshifted() {
        String expectedDateStr = "2026-07-03";
        when(metadataRepository.findValueByKey(Metadata.Key.LAST_UNSHIFTED.name()))
            .thenReturn(expectedDateStr);
            
        LocalDate result = metadataService.getLastUnshifted();
        assertEquals(LocalDate.parse(expectedDateStr), result);
    }
}
