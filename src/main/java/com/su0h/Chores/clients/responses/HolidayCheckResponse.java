package com.su0h.Chores.clients.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HolidayCheckResponse(
        @JsonProperty("isHoliday") boolean isHoliday,
        @JsonProperty("holidayName") String holidayName,
        @JsonProperty("requestedDate") String requestedDate
) {}