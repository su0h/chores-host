package com.su0h.Chores.services;

import com.su0h.Chores.clients.HolidayClient;
import com.su0h.Chores.entities.Holiday;
import com.su0h.Chores.repositories.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class DateService {
    private final HolidayRepository holidayRepository;
    private final HolidayClient holidayClient;

    public DateService(HolidayRepository holidayRepository, HolidayClient holidayClient) {
        this.holidayRepository = holidayRepository;
        this.holidayClient = holidayClient;
    }
    public boolean isDoubleTaskDay(LocalDate date) {
        return isWeekend(date) || isHoliday(date);
    }

    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isHoliday(LocalDate date) {
        return holidayClient.checkHoliday(date).isHoliday();
    }

    public Holiday getHolidayName(LocalDate date) {
        return holidayRepository.findByDate(date);
    }
}
