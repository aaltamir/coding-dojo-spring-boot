package com.assignment.spring.weather;

/**
 * Interface defining the contract for all weather providers
 */
public interface WeatherProvider {

    /**
     * Retruns a weather report for one specific city
     * @param city Name of the city
     * @return The weather for the specified city
     */
    WeatherEntity getWeather(String city);

}
