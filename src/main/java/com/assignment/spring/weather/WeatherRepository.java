package com.assignment.spring.weather;

import org.springframework.data.repository.CrudRepository;

interface WeatherRepository extends CrudRepository<WeatherEntity, Integer> {
}
