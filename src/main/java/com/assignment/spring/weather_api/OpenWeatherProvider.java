package com.assignment.spring.weather_api;

import com.assignment.spring.ApiErrorException;
import com.assignment.spring.CityNotFoundException;
import com.assignment.spring.weather.WeatherEntity;
import com.assignment.spring.weather.WeatherProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * Provides a weather report using Open Weather API
 */
@Service
@AllArgsConstructor
@Slf4j
public class OpenWeatherProvider implements WeatherProvider {

    private final RestTemplate restTemplate;

    private final ApiConfig config;

    @Override
    public WeatherEntity getWeather(final String city) {

        final String url = config.getApiUrl().replace("{city}", city).replace("{appid}", config.getAppId());

        log.info("Requesting weather using the following URL: {}", url);

        final ResponseEntity<WeatherResponse> response;

        try {
             response = restTemplate.getForEntity(url, WeatherResponse.class);
        } catch(HttpStatusCodeException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // We will suppose the city is not found
                throw new CityNotFoundException(e);
            } else {
                throw new ApiErrorException(e);
            }
        }

        return mapper(response.getBody());
    }

    private WeatherEntity mapper(WeatherResponse response) {
        return WeatherEntity.builder()
                .city(response.getName())
                .country(response.getSys().getCountry())
                .temperature(response.getMain().getTemp())
                .build();
    }
}


