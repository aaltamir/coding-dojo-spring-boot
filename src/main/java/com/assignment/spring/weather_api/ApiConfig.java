package com.assignment.spring.weather_api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("open-weather-api")
@Component
@Getter
@Setter
class ApiConfig {
    @NotBlank
    private String apiUrl;

    @NotBlank
    private String apiId;
}
