package com.assignment.spring.weather;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService service;

    @RequestMapping("/weather")
    public WeatherEntity weather(@RequestParam final String city) {
        log.info("Weather requested for city {}", city);
        return service.weather(city);
    }
}
