package com.su0h.Chores.clients;

import com.su0h.Chores.clients.exceptions.HolidayClientException;
import com.su0h.Chores.clients.responses.HolidayCheckResponse;
import com.su0h.Chores.clients.responses.HolidayResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.List;

@Component
public class HolidayClient {

    private final RestClient restClient;

    public HolidayClient(@Value("${holidays.api.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<HolidayResponse> getHolidays() {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/v1/holidays")
                            .build())
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<HolidayResponse>>() {});
        } catch (RestClientException e) {
            throw new HolidayClientException(
                    "Failed to fetch holidays: ", e
            );
        }
    }

    public HolidayCheckResponse checkHoliday(LocalDate date) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/v1/holidays/check")
                            .queryParam("date", date.toString())
                            .build())
                    .retrieve()
                    .body(HolidayCheckResponse.class);
        } catch (RestClientException e) {
            throw new HolidayClientException(
                    "Failed to check holiday for date: " + date, e
            );
        }
    }

    // Convenience overload — checks today's date
    public HolidayCheckResponse checkToday() {
        return checkHoliday(LocalDate.now());
    }
}