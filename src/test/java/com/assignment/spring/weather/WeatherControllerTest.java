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
public class WeatherControllerTest {

    @Mock
    private WeatherService service;

    @InjectMocks
    private WeatherController controller;

    @Mock
    private WeatherEntity mockWeather;

    @Test
    public void getWeatherTest() {
        when(service.weather(any())).thenReturn(mockWeather);
        final WeatherEntity weather = controller.weather("Amsterdam");
        verify(service).weather("Amsterdam");
        assertThat(weather).isSameAs(mockWeather);
    }
}