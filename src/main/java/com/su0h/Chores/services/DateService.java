package com.su0h.Chores.services;

import com.su0h.Chores.entities.Holiday;
import com.su0h.Chores.repositories.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class DateService {
    private final HolidayRepository holidayRepository;

    public DateService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }
    public boolean isDoubleTaskDay(LocalDate date) {
        return isWeekend(date) || isHoliday(date);
    }

    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isHoliday(LocalDate date) {
        Holiday holidayFound = holidayRepository.findByDate(date);
        return holidayFound != null;
    }
}
