package com.su0h.Chores.services;

import com.su0h.Chores.clients.HolidayClient;
import com.su0h.Chores.clients.responses.HolidayCheckResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DateServiceTest {

    @Mock
    private HolidayClient holidayClient;

    @InjectMocks
    private DateService dateService;

    @Test
    public void testIsWeekend() {
        assertTrue(dateService.isWeekend(LocalDate.of(2026, 7, 4))); // Saturday
        assertTrue(dateService.isWeekend(LocalDate.of(2026, 7, 5))); // Sunday
        assertFalse(dateService.isWeekend(LocalDate.of(2026, 7, 6))); // Monday
    }

    @Test
    public void testIsHoliday() {
        LocalDate holidayDate = LocalDate.of(2026, 12, 25);
        when(holidayClient.checkHoliday(holidayDate))
            .thenReturn(new HolidayCheckResponse(true, "Christmas", holidayDate.toString()));
            
        assertTrue(dateService.isHoliday(holidayDate));
        
        LocalDate regularDate = LocalDate.of(2026, 7, 6);
        when(holidayClient.checkHoliday(regularDate))
            .thenReturn(new HolidayCheckResponse(false, null, regularDate.toString()));
            
        assertFalse(dateService.isHoliday(regularDate));
    }
    
    @Test
    public void testIsDoubleTaskDay() {
        // It's a weekend, no need to check holiday because of short-circuiting
        LocalDate weekendDate = LocalDate.of(2026, 7, 4); // Saturday
        assertTrue(dateService.isDoubleTaskDay(weekendDate));
        verify(holidayClient, never()).checkHoliday(any());
        
        // It's a weekday but a holiday
        LocalDate holidayDate = LocalDate.of(2026, 12, 25); // Friday
        when(holidayClient.checkHoliday(holidayDate))
            .thenReturn(new HolidayCheckResponse(true, "Christmas", holidayDate.toString()));
        assertTrue(dateService.isDoubleTaskDay(holidayDate));
        
        // It's a weekday and not a holiday
        LocalDate regularDate = LocalDate.of(2026, 7, 6); // Monday
        when(holidayClient.checkHoliday(regularDate))
            .thenReturn(new HolidayCheckResponse(false, null, regularDate.toString()));
        assertFalse(dateService.isDoubleTaskDay(regularDate));
    }
}
