package com.assignment.spring.weather;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    private WeatherProvider provider;

    @Mock
    private WeatherRepository repository;

    @InjectMocks
    private WeatherService service;

    @Mock
    private WeatherEntity mockWeather;

    @Mock
    private WeatherEntity savedWeather;

    @Test
    public void getWeatherTest() {
        when(provider.getWeather(any())).thenReturn(mockWeather);
        when(repository.save(any())).thenReturn(savedWeather);

        final WeatherEntity weather = service.weather("London");

        verify(provider).getWeather("London");
        verify(repository).save(mockWeather);

        assertThat(weather).isSameAs(savedWeather);
    }

}