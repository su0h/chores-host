package com.su0h.Chores.controllers;

import com.su0h.Chores.entities.Holiday;
import com.su0h.Chores.services.DateService;
import com.su0h.Chores.services.HolidayService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;

@CrossOrigin(
        origins = {
                "http://localhost:4200",
                "https://localhost:4200",
                "http://127.0.0.1:4200",
                "https://127.0.0.1:4200",
                "http://192.168.1.92:4200",
                "https://192.168.1.92:4200",
                "http://chores.home.arpa",
                "https://chores.home.arpa"
        },
        maxAge = 3600
)
@RestController
@RequestMapping("/api/v1.1")
public class HolidayController {
    private final Logger logger = LoggerFactory.getLogger(HolidayController.class);
    private final HolidayService holidayService;
    private final DateService dateService;

    public HolidayController(
            HolidayService holidayService,
            DateService dateService
    ) {
        this.holidayService = holidayService;
        this.dateService = dateService;
    }

    @GetMapping("/holidays")
    public ResponseEntity<List<Holiday>> getAllHolidays() {
        logger.info("Request made to /holidays");
        return ResponseEntity.ok(holidayService.fetchAllHolidays());
    }

    @GetMapping("/is_holiday_today")
    public ResponseEntity<Boolean> isTodayAHoliday() {
        logger.info("Request made to /is_holiday_today");
        return ResponseEntity.ok(dateService.isHoliday(LocalDate.now()));
    }
}
