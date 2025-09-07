package com.su0h.Chores.holiday.dto;

import com.su0h.Chores.holiday.entity.Holiday;

import java.time.LocalDate;

public class HolidayResponse {
    private boolean isHoliday;
    private String holidayName;
    private LocalDate dateToday;


    public HolidayResponse(Holiday holiday, boolean isHoliday) {
        this.isHoliday = isHoliday;
        this.dateToday = LocalDate.now();

        if (holiday != null)
            holidayName = holiday.getName();

    }
    public LocalDate getDateToday() {
        return dateToday;
    }

    public void setDateToday(LocalDate dateToday) {
        this.dateToday = dateToday;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public boolean isHoliday() {
        return isHoliday;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }
}
