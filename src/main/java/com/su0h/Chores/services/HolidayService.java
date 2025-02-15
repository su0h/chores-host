package com.su0h.Chores.services;

import com.su0h.Chores.entities.Holiday;
import com.su0h.Chores.repositories.HolidayRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final Logger logger = LoggerFactory.getLogger(HolidayService.class);

    public HolidayService(
            HolidayRepository holidayRepository
    ) {
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> fetchAllHolidays() {
        this.logger.info("Returning list of all holidays");
        return holidayRepository.findAll();
    }
}
