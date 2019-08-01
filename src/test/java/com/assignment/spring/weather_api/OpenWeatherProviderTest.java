package com.assignment.spring.weather_api;

import com.assignment.spring.ApiErrorException;
import com.assignment.spring.CityNotFoundException;
import com.assignment.spring.weather.WeatherEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherProviderTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiConfig config;

    @InjectMocks
    private OpenWeatherProvider provider;

    @Mock
    private ResponseEntity<WeatherResponse> mockResponse;

    @Mock
    private WeatherResponse weatherResponse;

    @Mock
    private Sys sys;

    @Mock
    private Main main;

    @Mock
    private HttpStatusCodeException exception;

    @Test
    public void getWeatherReportTest() {
        when(config.getApiUrl()).thenReturn("http://url/{city}/{appid}");
        when(config.getAppId()).thenReturn("testAppId");

        when(restTemplate.getForEntity(anyString(), eq(WeatherResponse.class))).thenReturn(mockResponse);
        when(mockResponse.getBody()).thenReturn(weatherResponse);
        when(weatherResponse.getName()).thenReturn("London City");
        when(weatherResponse.getSys()).thenReturn(sys);
        when(sys.getCountry()).thenReturn("UK");
        when(weatherResponse.getMain()).thenReturn(main);
        when(main.getTemp()).thenReturn(33d);

        final WeatherEntity weather = provider.getWeather("London");

        verify(restTemplate).getForEntity("http://url/London/testAppId", WeatherResponse.class);

        assertThat(weather.getCity()).isEqualTo("London City");
        assertThat(weather.getCountry()).isEqualTo("UK");
        assertThat(weather.getTemperature()).isEqualTo(33d);
    }

    @Test(expected = CityNotFoundException.class)
    public void getCityNotFoundWhen404Test() {
        when(config.getApiUrl()).thenReturn("http://url/{city}/{appid}");
        when(config.getAppId()).thenReturn("testAppId");

        when(exception.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        when(restTemplate.getForEntity(anyString(), eq(WeatherResponse.class))).thenThrow(exception);

        provider.getWeather("London");
    }

    @Test(expected = ApiErrorException.class)
    public void getApiErrorWhenOtherTest() {
        when(config.getApiUrl()).thenReturn("http://url/{city}/{appid}");
        when(config.getAppId()).thenReturn("testAppId");

        when(exception.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        when(restTemplate.getForEntity(anyString(), eq(WeatherResponse.class))).thenThrow(exception);

        provider.getWeather("London");
    }

}

