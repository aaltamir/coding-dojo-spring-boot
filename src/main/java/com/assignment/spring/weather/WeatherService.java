package com.assignment.spring.weather;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service providing weather that also stores the information for future use.
 */
@Service
@AllArgsConstructor
public class WeatherService {
    private final WeatherProvider provider;

    private final WeatherRepository repository;

    public WeatherEntity weather(final String city) {
        return repository.save(provider.getWeather(city));
    }
}
